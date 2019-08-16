package com.zcs.test;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.util.Random;
import java.util.UUID;


/**
  * @author sxc 石新春
  * @Create date 2007-11-6
  */
public class MouseController implements Runnable {
    private Dimension dim;
    private Random rand;
    private Robot robot;
    private volatile boolean stop = false;

    public MouseController(){
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        rand = new Random();
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        while (!stop){
            int x = rand.nextInt(dim.width);
            System.out.println(x);
            int y = rand.nextInt(dim.height);
            System.out.println(y);
            //robot.mouseMove(x, y);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            try {
                Thread.sleep(2000);
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }
        }
    }
    public synchronized void stop() {
        stop = true;
    }
    public static void main(String[] args) {
        while (true){
            System.out.println(RandomStringUtils.randomNumeric(5));;
        }
    }
}


