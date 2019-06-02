package com.concurrent.util;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ToolUtil {



    public static void sleep(long time,TimeUnit timeUnit){
        try {
            switch (timeUnit){
                case DAYS:
                    TimeUnit.DAYS.sleep(time);
                    break;
                case HOURS:
                    TimeUnit.HOURS.sleep(time);
                    break;
                case MINUTES:
                    TimeUnit.MILLISECONDS.sleep(time);
                case SECONDS:
                    TimeUnit.SECONDS.sleep(time);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
