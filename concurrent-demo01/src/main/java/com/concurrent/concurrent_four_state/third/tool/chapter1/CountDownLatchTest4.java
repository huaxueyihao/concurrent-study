package com.concurrent.concurrent_four_state.third.tool.chapter1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class CountDownLatchTest4 {

    private static Random random = new Random(System.currentTimeMillis());

    static class Event {

        int id = 0;

        public Event(int id) {
            this.id = id;
        }
    }


    interface Watcher {
//        void startWatch();

        void done(Table table);
    }

    static class TaskBatch implements Watcher {

        private CountDownLatch countDownLatch;

        private TaskGroup taskGroup;

        public TaskBatch(int size, TaskGroup taskGroup) {
            this.countDownLatch = new CountDownLatch(size);
            this.taskGroup = taskGroup;
        }


        @Override
        public void done(Table table) {
            countDownLatch.countDown();
            if (countDownLatch.getCount() == 0) {
                System.out.println("The table " + table.tableName + " finished work,[" + table.toString() + "]");
                taskGroup.done(table);
            }

        }
    }

    static class TaskGroup implements Watcher {

        private CountDownLatch countDownLatch;

        private Event event;

        public TaskGroup(int size, Event e) {
            this.countDownLatch = new CountDownLatch(size);
            this.event = e;
        }


        @Override
        public void done(Table table) {
            countDownLatch.countDown();
            if (countDownLatch.getCount() == 0) {
//                System.out.println("The table " + table.tableName + " finished work,[" + table.toString() + "]");
                System.out.println("All of table done in event " + event.id);
            }

        }
    }

    static class Table {
        String tableName;
        long sourceRecordCount = 10;
        long targetCount;
        String columnSchema = "<table name='a><column name='clo1' type='varchar2'/></table>";
        String targetColumnSchema = "";

        public Table(String tableName, long sourceRecordCount) {
            this.tableName = tableName;
            this.sourceRecordCount = sourceRecordCount;
        }

        @Override
        public String toString() {
            return "Table{" +
                    "tableName='" + tableName + '\'' +
                    ", sourceRecordCount=" + sourceRecordCount +
                    ", targetCount=" + targetCount +
                    ", columnSchema='" + columnSchema + '\'' +
                    ", targetColumnSchema='" + targetColumnSchema + '\'' +
                    '}';
        }
    }

    private static List<Table> capture(Event event) {
        List<Table> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Table("table-" + i, i * 1000));

        }

        return list;
    }

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Event[] events = {new Event(1), new Event(2)};
        for (Event event : events) {
            List<Table> tables = capture(event);
            TaskGroup taskGroup = new TaskGroup(tables.size(), event);
            for (Table table : tables) {
                TaskBatch taskBatch = new TaskBatch(2, taskGroup);
                TrustSoourceRecorColumns trustSoourceRecorColumns = new TrustSoourceRecorColumns(table, taskBatch);
                TrustSoourceRecordCount trustSoourceRecordCount = new TrustSoourceRecordCount(table, taskBatch);


                executorService.submit(trustSoourceRecorColumns);
                executorService.submit(trustSoourceRecordCount);

            }
        }

    }

    static class TrustSoourceRecordCount implements Runnable {

        private final Table table;

        private final TaskBatch taskBatch;

        public TrustSoourceRecordCount(Table table, TaskBatch taskBatch) {
            this.table = table;
            this.taskBatch = taskBatch;
        }

        @Override
        public void run() {

            try {
                Thread.sleep(random.nextInt(10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            table.targetCount = table.sourceRecordCount;
            taskBatch.done(table);
//            System.out.println("The table " + table.tableName + " target count capture done and update data ");

        }
    }

    static class TrustSoourceRecorColumns implements Runnable {

        private final Table table;

        private final TaskBatch taskBatch;

        public TrustSoourceRecorColumns(Table table, TaskBatch taskBatch) {
            this.table = table;
            this.taskBatch = taskBatch;
        }

        @Override
        public void run() {

            try {
                Thread.sleep(random.nextInt(10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            table.targetColumnSchema = table.columnSchema;
            taskBatch.done(table);
//            System.out.println("The table " + table.tableName + " target columns capture done and update data ");

        }
    }


}
