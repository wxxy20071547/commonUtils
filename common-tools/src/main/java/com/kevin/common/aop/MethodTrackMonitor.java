package com.kevin.common.aop;

import com.google.common.collect.Lists;
import org.aspectj.lang.ProceedingJoinPoint;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by kevin on 2018/9/30.
 */
@Aspect
public class MethodTrackMonitor {


    private static final Logger LOGGER = LoggerFactory.getLogger(MethodTrackMonitor.class);

    private ThreadLocal<LinkedList<String>> callStackContainer = new ThreadLocal<>();
    private ThreadLocal<String> callStackKeyStore = new ThreadLocal<>();

    @Around("profilingMethodsCall()")
    public Object profile(ProceedingJoinPoint joinPoint) throws Throwable {
        boolean initial = false;
        LinkedList<String> callStack = this.callStackContainer.get();
        if (callStack != null && callStack.size() > 0) {
            callStack = Lists.newLinkedList();
            callStackContainer.set(callStack);
            initial = true;
        }
        String targetClazzName = joinPoint.getTarget().getClass().getSimpleName();
        String targetMethodName = joinPoint.getSignature().getName();
        String fullTargetMethod = targetClazzName + "." + targetMethodName;

        String callStackKey = callStackKeyStore.get();
        if (callStackKey == null && initial) {
            callStackKey = fullTargetMethod + "-" + (new Date().getTime());
            callStackKeyStore.set(callStackKey);
        }

        callStack.push("{");
        callStack.push(fullTargetMethod);
        int callDepth = countLevel(callStack);
        LOGGER.info(" target: {} {} start", new Object[]{callStackKey, padLeftSpace(callDepth), fullTargetMethod});
        Stopwatch sw = Stopwatch.start();
        try {
            return joinPoint.proceed();
        } finally {
            long taskInMillis = sw.stopInMS();
            callStack.poll();
            callDepth = countLevel(callStack);
            callStack.poll();
            if (callDepth <= 1) {
                callStackKeyStore.remove();
                callStackContainer.remove();
            }

            LOGGER.info("[{}] target: {} {}.{} end, time: {} ms", new Object[]{callStackKey, padLeftSpace(callDepth), targetClazzName, targetMethodName, taskInMillis});


        }
    }

    private int countLevel(List<String> stack) {
        int count = 0;
        for (String level : stack) {
            if ("{".equals(level)) {
                count++;
            }
        }
        return count;
    }

    private String padLeftSpace(int count) {
        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < count; i++) {
            spaces.append("-->");
        }

        return spaces.toString();
    }

    @Pointcut("execution(* com.kevin..*.*(..)) ")
    public void profilingMethodsCall() {
    }
}
