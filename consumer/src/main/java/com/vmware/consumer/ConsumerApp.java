package com.vmware.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.function.Consumer;

import static java.lang.Boolean.TRUE;

@SpringBootApplication
@Slf4j
public class ConsumerApp {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApp.class, args);
    }

    @Bean
    public Consumer<Flux<Map<String, Object>>> consumer() {
        log.info("consumer");
        return flux -> flux
                .doOnNext(map -> log.info("consumer: 1: map = {}", map))
                .filter(map -> TRUE.equals(map.get("filter")))
                .doOnNext(map -> log.info("consumer: 2: map = {}", map))
                .map(map -> {
                    Number count = (Number) map.get("count");
                    Number amount = (Number) map.get("amount");
                    Number total = count.intValue() * amount.doubleValue();
                    map.put("total", total);
                    return map;
                })
                .doOnNext(map -> log.info("consumer: 3: map = {}", map))
                .subscribe(map -> log.info("consumer: 4: map = {}", map));
    }
}
