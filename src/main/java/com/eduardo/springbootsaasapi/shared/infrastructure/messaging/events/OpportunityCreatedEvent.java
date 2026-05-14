package com.eduardo.springbootsaasapi.shared.infrastructure.messaging.events;

import java.time.LocalDateTime;

public record OpportunityCreatedEvent(

        Integer opportunityId,

        Integer ownerId,

        String name,

        String stage,

        Integer probability,

        LocalDateTime createdAt

) {
}
