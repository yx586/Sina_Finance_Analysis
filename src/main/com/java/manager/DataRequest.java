package com.java.manager;

import com.alibaba.fastjson.JSON;
import com.java.consts.Constant;
import com.java.utils.File_Write_Load;
import com.java.utils.HttpRequestUtil;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static com.java.consts.Constant.CHARSET_GBK;
import static com.java.consts.Constant.ONE_PAGE;
import static com.java.consts.Constant.SINA_MAGIC_PATTERN_URL;
import static com.java.kafka.SimpleProducer.sendMessage;

public class DataRequest {

    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

    public void dataRequest() {
        String info = HttpRequestUtil.get(SINA_MAGIC_PATTERN_URL + ONE_PAGE, CHARSET_GBK);
        String count = JSON.parseObject(info.replace(Constant.HEAD_PREFIX, "").replace(Constant.TAIL_PREFIX, "")).getString("count");
        int size = (int) Math.ceil((double) Integer.parseInt(count) / Constant.PAGE_SIZE);
        final CountDownLatch taskLatch = new CountDownLatch(size - Constant.DIFF_);
        String url = SINA_MAGIC_PATTERN_URL;
        String charset = CHARSET_GBK;
        // 保存异步处理的结果
        List<Future<Boolean>> result_boolean = new ArrayList<>(size - Constant.DIFF_);

        try {
            for (int i = 1; i < size + 1; i++) {
                fixedThreadPool.submit(new RequestRunnable(url + i, charset,i, taskLatch));
            }
            fixedThreadPool.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class RequestRunnable implements Callable<Boolean> {
        private String url;
        private String charset;
        private CountDownLatch latch;
        private int num;

        public RequestRunnable(final String url, final String charset,final int num, CountDownLatch latch) {
            this.url = url;
            this.charset = charset;
            this.latch = latch;
            this.num = num;
        }

        @Override
        public Boolean call() throws Exception {
            boolean flag = true;
            try {
                String response = HttpRequestUtil.get(url, charset);
                JSON result_json = JSON.parseObject(response.replace(Constant.HEAD_PREFIX, "").replace(Constant.TAIL_PREFIX, ""));
                String result = result_json.toString();
                sendMessage(result);
            } catch (Exception e) {
                flag = false;
            } finally {
                latch.countDown();
            }
            return flag;
        }

    }

    public static void logs(String log) {
        System.out.println("" + log + "\n");
    }

}
