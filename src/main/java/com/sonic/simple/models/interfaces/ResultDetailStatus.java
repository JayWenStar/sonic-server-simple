package com.sonic.simple.models.interfaces;

/**
 * 跟 {@link ResultStatus 一致即可}
 */
public interface ResultDetailStatus {
    /**
     * 任务分发中
     */
    int DISTRIBUTING = -1;
    /**
     * 任务进行中
     */
    int RUNNING = 0;
    int PASS = 1;
    int WARN = 2;
    int FAIL = 3;
}
