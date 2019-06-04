package com.concurrent.concurrent_four_state.third.tool.chapter10;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class SimpleJob implements Job {


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("=====================" + System.currentTimeMillis());
    }


}
