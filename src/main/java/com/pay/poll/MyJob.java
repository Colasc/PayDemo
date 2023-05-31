package com.pay.poll;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class MyJob implements Job {
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("扫库。。。");
    }

    public static void main(String[] args) throws Exception {
        //创建定时任务
        JobDetail jobDetail = JobBuilder.newJob(Job.class).withIdentity("job1","group1").build();

        //创建触发器，每3秒钟执行一次
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1","group3")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever()).build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();

        //将任务及其触发器放置在调度器
        scheduler.scheduleJob(jobDetail,trigger);

        scheduler.start();

    }
}
