package com.cloud.core.utils;

import lombok.experimental.UtilityClass;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName BusinessUtil
 * @Description: 业务工具类
 * @Author kevins
 * @Date 2019/12/6
 * @Version V1.0
 **/
@UtilityClass
public class BusinessUtil {
    static AtomicInteger ai = new AtomicInteger(0);

    /**
     * @return int
     * @Author kevins
     * @Description 0-254循环计数
     * @Date 5:05 下午 2019/12/12
     * @Param []
     **/
    public static int get() {
        int result = ai.get();
        if (result == 254) {
            ai.getAndSet(0);
        } else {
            ai.getAndIncrement();//以原子的方式将当前值加 1，注意，这里返回的是自增前的值，也就是旧值
        }
        return result;
    }

//    public static void main(String[] args) {
//        for (int i = 0; i < 600; i++) {
//            System.out.print(get() + ",");
//        }
//    }

    /**
     * @return java.lang.Integer
     * @Author kevins
     * @Description 大于最高分取最高分，小于最低分取最低分，否则取当前分数
     * @Date 4:28 下午 2019/12/10
     * @Param [score, high, low]
     **/
    public static Integer BestScore(Integer score, Integer high, Integer low) {
        if (score > high) {
            return high;
        } else if (score < low) {
            return low;
        } else {
            return score;
        }
    }
}
