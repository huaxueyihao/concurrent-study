package com.concurrent.practice.chapter05;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.LinkedBlockingDeque;

public class MainClass {

    private static final int N_CONSUMERS = 10;

    public static void main(String[] args) {

        File[] roots = {
            new File("/Users/amao/Documents")
        };
        startIndexing(roots);

    }

    private static void startIndexing(File[] roots) {
        LinkedBlockingDeque<File> queue = new LinkedBlockingDeque<File>(10);
        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return true;
            }
        };

        for (File root : roots) {
            new Thread(new FileCrawler(queue,fileFilter,root)).start();
        }

        for (int i = 0; i < N_CONSUMERS; i++) {
            new Thread(new Indexer(queue)).start();
        }


    }
}
