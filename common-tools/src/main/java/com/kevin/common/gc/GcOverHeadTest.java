package com.kevin.common.gc;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * [GC (Allocation Failure) [PSYoungGen: 2038K->512K(2560K)] 2038K->986K(9728K), 0.0011218 secs] [Times: user=0.00
 * sys=0.00, real=0.00 secs] [GC (Allocation Failure) [PSYoungGen: 2560K->496K(2560K)] 3034K->1773K(9728K), 0.0025579
 * secs] [Times: user=0.00 sys=0.00, real=0.00 secs] [GC (Allocation Failure) [PSYoungGen: 2544K->480K(2560K)]
 * 3821K->3622K(9728K), 0.0195188 secs] [Times: user=0.02 sys=0.00, real=0.02 secs] [GC (Allocation Failure)
 * [PSYoungGen: 2419K->512K(2560K)] 5561K->5588K(9728K), 0.0274360 secs] [Times: user=0.01 sys=0.00, real=0.03 secs]
 * [Full GC (Ergonomics) [PSYoungGen: 512K->0K(2560K)] [ParOldGen: 5076K->5150K(7168K)] 5588K->5150K(9728K), [Metaspace:
 * 3363K->3363K(1056768K)], 0.1387076 secs] [Times: user=0.27 sys=0.00, real=0.14 secs] [Full GC (Ergonomics)
 * [PSYoungGen: 2048K->0K(2560K)] [ParOldGen: 5150K->6787K(7168K)] 7198K->6787K(9728K), [Metaspace:
 * 3363K->3363K(1056768K)], 0.0652487 secs] [Times: user=0.11 sys=0.00, real=0.07 secs] [Full GC (Ergonomics)
 * [PSYoungGen: 2048K->2048K(2560K)] [ParOldGen: 7048K->7048K(7168K)] 9096K->9096K(9728K), [Metaspace:
 * 3363K->3363K(1056768K)], 0.0445140 secs] [Times: user=0.14 sys=0.00, real=0.04 secs] Exception in thread "main" [Full
 * GC (Ergonomics) java.lang.OutOfMemoryError: GC overhead limit exceeded at
 * java.lang.Integer.toString(Integer.java:403) at java.lang.String.valueOf(String.java:3099) [PSYoungGen:
 * 2048K->0K(2560K)] [ParOldGen: 7064K->924K(7168K)] 9112K->924K(9728K), [Metaspace: 3388K->3388K(1056768K)], 0.0143516
 * secs] [Times: user=0.03 sys=0.00, real=0.01 secs] at com.kevin.common.gc.GcOverHeadTest.main(GcOverHeadTest.java:15)
 * Heap PSYoungGen total 2560K, used 58K [0x00000000ffd00000, 0x0000000100000000, 0x0000000100000000) eden space 2048K,
 * 2% used [0x00000000ffd00000,0x00000000ffd0ea90,0x00000000fff00000) from space 512K, 0% used
 * [0x00000000fff80000,0x00000000fff80000,0x0000000100000000) to space 512K, 0% used
 * [0x00000000fff00000,0x00000000fff00000,0x00000000fff80000) ParOldGen total 7168K, used 924K [0x00000000ff600000,
 * 0x00000000ffd00000, 0x00000000ffd00000) object space 7168K, 12% used
 * [0x00000000ff600000,0x00000000ff6e7228,0x00000000ffd00000) Metaspace used 3395K, capacity 4600K, committed 4864K,
 * reserved 1056768K class space used 371K, capacity 424K, committed 512K, reserved 1048576K
 */

public class GcOverHeadTest {

    /**
     * 
     * java.lang.OutOfMemoryError: GC overhead limit exceeded 设置启动参数 -Xms10m -Xmx10m -XX:+PrintGCDetails
     * -XX:MaxDirectMemorySize=5m
     * 
     * @param args
     */
    public static void main(String[] args) {
        int i = 0;
        List<String> list = Lists.newArrayList();
        try {
            for (;;) {
                list.add(String.valueOf(++i).intern());
            }
        } catch (Exception e) {
            e.getStackTrace();
            throw e;
        }

    }
}
