package com.eduardo.springbootsaasapi.shared.infrastructure.messaging.publisher;

import lombok.NonNull;

public interface EventPublisher {

    void publish(
            @NonNull String channel,
            @NonNull Object payload);
}