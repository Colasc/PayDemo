package com.pay.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

public class DelayQueDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("000000001");
        list.add("000000002");
        list.add("000000003");
        list.add("000000004");
        list.add("000000005");

        DelayQueue<OrderDelay> queue = new DelayQueue<OrderDelay>();

        long start = System.currentTimeMillis();
        //延迟3秒取出订单
        for (int i = 0; i < 5; i++) {
            queue.put(new OrderDelay(list.get(i), TimeUnit.NANOSECONDS.convert(3,TimeUnit.SECONDS)));
            try {
                queue.take().print();
                System.out.println("After" + (System.currentTimeMillis()-start) + "MillSeconds");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
