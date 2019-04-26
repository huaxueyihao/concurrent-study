package com.concurrent.practice_video.thread_create.spring_create;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainClass {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);
        DemoService ds = ac.getBean(DemoService.class);
        ds.a();
        ds.b();

    }
}
