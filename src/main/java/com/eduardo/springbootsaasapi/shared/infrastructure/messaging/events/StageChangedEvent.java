package com.eduardo.springbootsaasapi.shared.infrastructure.messaging.events;

import java.time.LocalDateTime;

public record StageChangedEvent(

        Integer opportunityId,

        Integer ownerId,

        String previousStage,

        String currentStage,

        LocalDateTime changedAt

) {

}
