package com.sonic.simple.models.interfaces;

/**
 * 跟 {@link ResultDetailStatus 一致即可}
 *
 * @author ZhouYiXun
 * @des 定义状态
 * @date 2021/8/15 19:50
 */
public interface ResultStatus {
    /**
     * 任务分发中
     */
    int DISTRIBUTING = -1;
    /**
     * 任务进行中
     */
    int RUNNING = 0;
    /**
     * 测试通过
     */
    int PASS = 1;
    /**
     * 中断警告
     */
    int WARNING = 2;
    /**
     * 测试失败
     */
    int FAIL = 3;
}
