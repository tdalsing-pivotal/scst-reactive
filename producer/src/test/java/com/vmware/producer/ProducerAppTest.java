package com.vmware.producer;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
class ProducerAppTest {

    WebClient client = WebClient.create("http://localhost:8401");

    @Test
    void post() throws Exception {
        for (int i = 0; i < 10; ++i) {
            Map<String, Object> map = new HashMap<>();

            map.put("id", "id-" + i);
            map.put("name", "name-" + i);
            map.put("count", i);
            map.put("amount", 10.0 * i);
            map.put("filter", i % 5 != 0);

            log.info("post: map = {}", map);

            client
                    .post()
                    .uri("/")
                    .contentType(APPLICATION_JSON)
                    .bodyValue(map)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
        }
    }
}