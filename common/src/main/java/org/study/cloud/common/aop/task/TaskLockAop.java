package org.study.cloud.common.aop.task;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * 定时任务分布式锁
 * @author lipo
 * @version v1.0
 * @date 2019-10-14 14:40
 */
@Component
@Aspect
@Slf4j
public class TaskLockAop {

    @Autowired
    private RedisLockRegistry redisLockRegistry;

    @Around("execution(@TaskLock * * (..))")
    public Object taskAround(ProceedingJoinPoint pjp) throws Throwable {

        TaskLock taskAnnotation = ((MethodSignature)pjp.getSignature()).getMethod().getAnnotation(TaskLock.class);

        String lockKey = taskAnnotation.value();
        Lock lock = redisLockRegistry.obtain(lockKey);
        try {
            lock.tryLock(30L, TimeUnit.SECONDS);
            log.info("任务开始, {}", lockKey);

            return pjp.proceed();

        } finally {
            lock.unlock();
            log.info("任务结束, ", lockKey);
        }
    }


}
