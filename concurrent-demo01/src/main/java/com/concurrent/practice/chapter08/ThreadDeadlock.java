package com.concurrent.practice.chapter08;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程死锁的例子
 */
public class ThreadDeadlock {

    ExecutorService exec = Executors.newSingleThreadExecutor();

    public class LoadFileTask implements Callable<String> {
        private final String fileName;

        public LoadFileTask(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public String call() throws Exception {

            return "";
        }
    }

    public class RenderPageTask implements Callable<String> {


        @Override
        public String call() throws Exception {
            Future<String> header, footer;
            header = exec.submit(new LoadFileTask("header.html"));
            footer = exec.submit(new LoadFileTask("footer.html"));

            String page = renderBody();
            return header.get() + page + footer.get();
        }

        private String renderBody() {
            // Here.s where we would actully render the page;
            return "";
        }


    }


}
