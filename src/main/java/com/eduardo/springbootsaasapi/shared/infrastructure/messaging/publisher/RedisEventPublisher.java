package com.eduardo.springbootsaasapi.shared.infrastructure.messaging.publisher;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisEventPublisher implements EventPublisher {

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void publish(
            @NonNull String channel,
            @NonNull Object payload) {

        System.out.println(
                "Publishing event -> Channel: "
                        + channel
                        + " Payload: "
                        + payload);

        redisTemplate.convertAndSend(
                channel,
                payload);
    }
}
