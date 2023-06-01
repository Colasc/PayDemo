package com.pay.hashWheel;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;

import java.util.concurrent.TimeUnit;

public class HashWheelTimerTest {
    static class MyTimerTask implements TimerTask{

        boolean flag;

        public MyTimerTask(boolean flag){
            this.flag = flag;
        }

        public void run(Timeout timeout) throws Exception {
            System.out.println("数据库删订单。。。");
            this.flag = false;  //任务完成标志变化，不再执行任务
        }
    }

    public static void main(String[] args) {
        MyTimerTask timerTask = new MyTimerTask(true);
        Timer timer = new HashedWheelTimer();
        //自定义轮转时间，本次延迟5秒去删除订单
        timer.newTimeout(timerTask,5, TimeUnit.SECONDS);

        int i = 1;
        while (timerTask.flag){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(i + "秒过去了");
            i++;
        }
    }
}
