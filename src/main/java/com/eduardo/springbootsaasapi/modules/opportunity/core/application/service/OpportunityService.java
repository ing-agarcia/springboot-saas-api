package com.eduardo.springbootsaasapi.modules.opportunity.core.application.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eduardo.springbootsaasapi.modules.opportunity.core.application.dto.OpportunityCreateDTO;
import com.eduardo.springbootsaasapi.modules.opportunity.core.application.dto.OpportunityDTO;
import com.eduardo.springbootsaasapi.modules.opportunity.core.application.mappers.OpportunityMapper;
import com.eduardo.springbootsaasapi.modules.opportunity.core.domain.entities.Opportunity;
import com.eduardo.springbootsaasapi.modules.opportunity.core.domain.repositories.OpportunityRepository;
import com.eduardo.springbootsaasapi.shared.infrastructure.exceptions.BadRequestException;
import com.eduardo.springbootsaasapi.shared.infrastructure.messaging.enums.OpportunityEventType;
import com.eduardo.springbootsaasapi.shared.infrastructure.messaging.events.OpportunityCreatedEvent;
import com.eduardo.springbootsaasapi.shared.infrastructure.messaging.events.ProbabilityChangedEvent;
import com.eduardo.springbootsaasapi.shared.infrastructure.messaging.events.StageChangedEvent;
import com.eduardo.springbootsaasapi.shared.infrastructure.messaging.publisher.EventPublisher;

@Service
public class OpportunityService {

    private final OpportunityRepository opportunityRepository;
    private final OpportunityMapper opportunityMapper;
    private final EventPublisher eventPublisher;

    public OpportunityService(
            OpportunityRepository opportunityRepository,
            OpportunityMapper opportunityMapper,
            EventPublisher eventPublisher) {
        this.opportunityRepository = opportunityRepository;
        this.opportunityMapper = opportunityMapper;
        this.eventPublisher = eventPublisher;
    }

    public OpportunityDTO newOpportunity(OpportunityCreateDTO saveOpportunityDTO) {

        Opportunity opportunity = opportunityMapper.toDomainCreate(saveOpportunityDTO);
        Optional<Opportunity> existingOpportunity = opportunityRepository.findByName(opportunity.getName());
        if (existingOpportunity.isPresent()
                &&
                !existingOpportunity.get()
                        .getId()
                        .equals(opportunity.getId())) {
            throw new BadRequestException(
                    "Opportunity already exists");
        }

        boolean isNew = opportunity.getId() == null;

        Optional<Opportunity> currentOpp = Optional.empty();
        if (opportunity.getId() != null) {
            currentOpp = opportunityRepository.findById(opportunity.getId());
        }

        Optional<OpportunityDTO> currentDto = currentOpp.map(opportunityMapper::toDto);

        Opportunity saved = opportunityRepository.save(opportunity);
        OpportunityDTO opportunityDTO = opportunityMapper.toDto(saved);

        if (isNew) {
            handleCreatedEvent(opportunityDTO);
        }

        currentDto.ifPresent(current -> {

            if (!current.stage().equals(opportunityDTO.stage())) {
                handleStageEvent(opportunityDTO, current);
            }

            if (!current.probability()
                    .equals(opportunityDTO.probability())) {

                handleProbabilityEvent(opportunityDTO, current);
            }
        });

        return opportunityDTO;

    }

    private void handleCreatedEvent(OpportunityDTO opportunityDto) {

        OpportunityCreatedEvent event = new OpportunityCreatedEvent(
                opportunityDto.id(),
                opportunityDto.ownerId(),
                opportunityDto.name(),
                opportunityDto.stage(),
                opportunityDto.probability(),
                opportunityDto.createdAt());

        eventPublisher.publish(
                OpportunityEventType.OPPORTUNITY_CREATED.name(),
                event);
    }

    private void handleStageEvent(OpportunityDTO opportunityDto, OpportunityDTO currentDto) {

        StageChangedEvent event = new StageChangedEvent(
                opportunityDto.id(),
                opportunityDto.ownerId(),
                currentDto.stage(),
                opportunityDto.stage(),
                LocalDateTime.now());

        eventPublisher.publish(
                OpportunityEventType.STAGE_CHANGED.name(),
                event);
    }

    private void handleProbabilityEvent(OpportunityDTO opportunityDto, OpportunityDTO currentDto) {

        boolean increased = opportunityDto.probability() > currentDto.probability();
        ProbabilityChangedEvent event = new ProbabilityChangedEvent(
                opportunityDto.id(),
                opportunityDto.ownerId(),
                currentDto.probability(),
                opportunityDto.probability(),
                increased,
                LocalDateTime.now());

        eventPublisher.publish(
                increased ? OpportunityEventType.PROBABILITY_INCREASED.name()
                        : OpportunityEventType.PROBABILITY_DECREASED.name(),
                event);
    }

    public Page<OpportunityDTO> getOpportunitiesByUserId(Integer userId, Pageable pageable) {

        return opportunityRepository.findAllByUserId(userId, pageable)
                .map(opportunityMapper::toDto);
    }
}
