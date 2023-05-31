package com.pay.queue;

import java.lang.ref.SoftReference;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class OrderDelay implements Delayed {

    private String orderId;

    private long timeout;

    OrderDelay(String orderId,long timeout){
        this.orderId = orderId;
        this.timeout = timeout;
    }

    /**
     * 返回距离我们所设置的超时时间还剩多久
     * @param unit the time unit
     * @return
     */
    public long getDelay(TimeUnit unit) {
        return unit.convert(timeout - System.nanoTime(),TimeUnit.NANOSECONDS);
    }

    public int compareTo(Delayed o) {

        if (o == this){
            return 0;
        }
        OrderDelay  t = (OrderDelay) o;
        long d = (getDelay(TimeUnit.NANOSECONDS)-t.getDelay(TimeUnit.NANOSECONDS));

        return (d == 0) ? 0 : ((d < 0) ? -1 : 1);
    }

    void print(){
        System.out.println("编号"+orderId+"订单即将删除!");
    }
}
