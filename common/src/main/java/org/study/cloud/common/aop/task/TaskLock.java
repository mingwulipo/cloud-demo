package org.study.cloud.common.aop.task;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @desc: 定时任务分布式锁的注解
 * @author: lipo
 * @date: 2019-10-14 14:38
 * @version: v1.0
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface TaskLock {
    String value();
}
