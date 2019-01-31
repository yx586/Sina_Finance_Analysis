package com.java.job;


public class Job {
    public final static long ONE_Minute = 60 * 1000;//1分钟
    public final static long FIFTEEN_Minute = 5 * 60 * 1000;// 15分钟
    public final static long THIRTY_Minute = 30 * 60 * 1000; //30分钟
    public final static long ONE_HOUR_Minute = 60 * 60 * 1000; //30分钟

//    @Scheduled(fixedDelay = FIFTEEN_Minute)
//    public void fixedDelayJob() {
//        DataManager.getInstance().RequestData(Constant.SINA_MAGIC_PATTERN_URL, false);
//        System.out.println(new Date().toString() + " >> wait..... ");
//        String format = DateFormatUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss");
//        System.out.println(format + " >>fixedDelay执行....");
//    }
//
//    @Scheduled(cron = "0 30/15 22,23 * * MON-FRI")
//    public void cronJob() {
//        //10点开始每15分钟拉一次数据
//        DataManager.getInstance().RequestData(Constant.SINA_MAGIC_PATTERN_URL, false);
//        String format = DateFormatUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss");
//        System.out.println(format + " >>cron执行....");
//    }

}

