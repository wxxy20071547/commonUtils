package com.kevin.common.limitStream;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * Created by kevin on 2018/5/14.
 * RateLimiter限流（单应用，分布式考虑redis实现）
 *
 * Guava中开源出来一个令牌桶算法的工具类RateLimiter，可以轻松实现限流的工作。RateLimiter 对简单的令牌桶算法做了一些工程上的优化，
 * 具体的实现是 SmoothBursty。需要注意的是，RateLimiter 的另一个实现 SmoothWarmingUp，就不是令牌桶了，而是漏桶算法。也许是出于简单起见，
 * RateLimiter 中的时间窗口能且仅能为 1s，如果想搞其他时间单位的限流，只能另外造轮子。RateLimiter 有一个有趣的特性是「前人挖坑后人跳」，
 * 也就是说 RateLimiter 允许某次请求拿走超出剩余令牌数的令牌，但是下一次请求将为此付出代价，一直等到令牌亏空补上，并且桶中有足够本次请求使用的令牌为止。
 * 这里面就涉及到一个权衡，是让前一次请求干等到令牌够用才走掉呢，还是让它先走掉后面的请求等一等呢？Guava 的设计者选择的是后者，先把眼前的活干了，后面的事后面再说。
 */
public class RateLimiterTestController {
    /**
     * 令牌桶容量，一秒生成5个令牌
     **/
    private static final double PERMITS = 5;
    RateLimiter rateLimiter = RateLimiter.create(PERMITS);


    public String getOrderNO() {
        /**  tryAcquire(long timeout, TimeUnit unit)
         * 从RateLimiter 获取许可如果该许可可以在不超过timeout的时间内获取得到的话，
         *  或者如果无法在timeout 过期之前获取得到许可的话，那么立即返回false（无需等待）
         * */

        if (!rateLimiter.tryAcquire(1000, TimeUnit.MILLISECONDS)) {
            System.out.println("获取失败!");
            return  "error!";
        }
        String orderNo = "NO" + System.currentTimeMillis();
        System.out.println("获取成功：" + orderNo);
        return  orderNo;
    }


    public static void main(String[] args) {
        RateLimiter limit = RateLimiter.create(5);
        System.out.println(limit.acquire(6));
        System.out.println(limit.acquire(4));
        System.out.println(limit.acquire(4));
        System.out.println(limit.acquire(4));

    }


}
