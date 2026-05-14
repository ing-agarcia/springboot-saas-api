package com.eduardo.springbootsaasapi.shared.infrastructure.messaging.events;

import java.time.LocalDateTime;

public record ProbabilityChangedEvent(

        Integer opportunityId,

        Integer ownerId,

        Integer previousProbability,

        Integer currentProbability,

        boolean increased,

        LocalDateTime changedAt) {

}
