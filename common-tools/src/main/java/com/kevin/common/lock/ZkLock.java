package com.kevin.common.lock;

import com.google.common.base.Strings;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by kevin on 2018/5/29.
 */

public class ZkLock {

    private String zkQurom = "10.42.60.140:2181";
    private String lockName = "/kevinLock";
    private String lockZnode = null;
    private ZooKeeper zk;

    public ZkLock() {
        try {
            zk = new ZooKeeper(zkQurom, 6000, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.out.println("Receive event " + watchedEvent);
                    if (Event.KeeperState.SyncConnected == watchedEvent.getState())
                        System.out.println("connection is established...");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ensureRootPath() {
        try {
            if (zk.exists(lockName, true) == null) {
                zk.create(lockName, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);//根目录是持久类型
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取锁 * @return * @throws InterruptedException
     */
    public void lock() {
        String path = null;
        ensureRootPath();
        try {
            path = zk.create(lockName + "/kevinLock_", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);//子目录是临时顺序型
            lockZnode = path;
            List<String> minPath = zk.getChildren(lockName, false);
            System.out.println(minPath);
            Collections.sort(minPath);
            System.out.println(minPath.get(0) + " and path " + path);
            if (!Strings.nullToEmpty(path).trim().isEmpty() && !Strings.nullToEmpty(minPath.get(0)).trim().isEmpty() && path.equals(lockName + "/" + minPath.get(0))) {
                System.out.println(Thread.currentThread().getName() + " get Lock...");
                return;
            }
            String watchNode = null;
            for (int i = minPath.size() - 1; i >= 0; i--) {
                if (minPath.get(i).compareTo(path.substring(path.lastIndexOf("/") + 1)) < 0) {
                    watchNode = minPath.get(i);
                    break;
                }
            }
            if (watchNode != null) {
                final String watchNodeTmp = watchNode;
                final Thread thread = Thread.currentThread();
                Stat stat = zk.exists(lockName + "/" + watchNodeTmp, new Watcher() {
                    @Override
                    public void process(WatchedEvent watchedEvent) {
                        if (watchedEvent.getType() == Event.EventType.NodeDeleted) {
                            thread.interrupt();
                        }
                        try {
                            zk.exists(lockName + "/" + watchNodeTmp, true);
                        } catch (KeeperException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                if (stat != null) {
                    System.out.println("Thread " + Thread.currentThread().getId() + " waiting for " + lockName + "/" + watchNode);
                }
            }
            try {
                Thread.sleep(1000000000);
            } catch (InterruptedException ex) {
                System.out.println(Thread.currentThread().getName() + " notify");
                System.out.println(Thread.currentThread().getName() + " get Lock...");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放锁
     */
    public void unlock() {
        try {
            System.out.println(Thread.currentThread().getName() + "release Lock...");
            zk.delete(lockZnode, -1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 4; i++) {
            service.execute(() -> {
                ZkLock test = new ZkLock();
                try {
                    test.lock();
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                test.unlock();
            });
        }
        service.shutdown();
    }


}
