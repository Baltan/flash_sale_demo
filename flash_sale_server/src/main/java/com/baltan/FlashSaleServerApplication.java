package com.baltan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <pre>
 * 启动RabbitMQ流程：
 * 1、进入/usr/local/Cellar/rabbitmq/版本号/sbin目录
 * 2、执行rabbitmq-server -detached命令后台启动RabbitMQ
 * 3、可以通过rabbitmqctl status命令查看RabbitMQ状态
 * 4、可以在浏览器中访问http://localhost:15672查看RabbitMQ可视化界面
 *
 * 关闭RabbitMQ流程：
 * 1、进入/usr/local/Cellar/rabbitmq/版本号/sbin目录
 * 2、执行rabbitmqctl stop命令
 * </pre>
 */
@SpringBootApplication
public class FlashSaleServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(FlashSaleServerApplication.class, args);
    }
}
