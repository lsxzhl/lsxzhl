package com.zhl.servicehi.interview.fly;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/8/2 16:19
 */
public class FightQueryTask extends Thread implements FightQuery {

    private final String origin;
    private final String destination;
    private final List<String> flightList = new ArrayList<>();

    public FightQueryTask(String airLine, String origin, String destination) {
        super("[" + airLine + "]");
        this.origin = origin;
        this.destination = destination;
    }

    @Override
    public void run() {
        System.out.printf("%s-query from $s to %s \n", getName(), origin, destination);
        int randomVal = ThreadLocalRandom.current().nextInt(10);
        try {
            TimeUnit.SECONDS.sleep(randomVal);
            this.flightList.add(getName() + "-" + randomVal);
            System.out.printf("The flight:$s list query successful\n", getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> get() {
        return this.flightList;
    }
}
