package io.zeebe.camel.example.amqp;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.SmartLifecycle;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import io.zeebe.camel.example.amqp.model.Order;
import lombok.extern.slf4j.Slf4j;

// Enable if you want to configure an embedded activeMQ server using Spring XML Configuration
// @Configuration
// @ImportResource(value = "classpath:spring-activemq-config.xml")
@SpringBootApplication
@Slf4j
@EnableScheduling
public class Application implements SmartLifecycle {

    @Autowired
    private OrderSender orderSender;

    private boolean isRunning;

    private final AtomicInteger count = new AtomicInteger(0);

    @Scheduled(fixedDelay = 3000L)
    public void run() throws Exception {
        log.info("Spring Boot Embedded ActiveMQ Configuration Example");

        Order myMessage = new Order(count.getAndIncrement() + " - Sending JMS Message using Embedded activeMQ", new Date());
        orderSender.send(myMessage);

    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable runnable) {
        runnable.run();
    }

    @Override
    public void start() {
        isRunning = true;
    }

    @Override
    public void stop() {
        this.stop(() -> {});
    }

    @Override public boolean isRunning() {
        return isRunning;
    }

    @Override public int getPhase() {
        return Integer.MAX_VALUE;
    }
}
