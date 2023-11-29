package com.example.historyservice.service.impl;

import com.example.historyservice.dto.HistoryDTO;
import com.example.historyservice.entity.History;
import com.example.historyservice.enums.Action;
import com.example.historyservice.event.HistorySaveEvent;
import com.example.historyservice.mapper.HistoryMapper;
import com.example.historyservice.repository.HistoryRepository;
import com.example.historyservice.service.HistoryService;
import com.google.common.base.Ticker;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;
    private final HistoryMapper historyMapper;

    @Transactional
    @Override
    public HistoryDTO createNewHistory(HistorySaveEvent historySaveEvent) {
        log.info("Creating new history log");

        History history=historyMapper.mapToHistory(historySaveEvent);

        history.setAction(Action.valueOf(historySaveEvent.getAction().name()));
        history.setDate(LocalDate.now());

        return historyMapper.mapFromEntity(historyRepository.save(history));
    }

    @Override
    public List<HistoryDTO> getAllHistory(Long ticketId) throws EntityNotFoundException {
        log.info("Finding all history");

        List<History> histories = historyRepository.findAllByTicketId(ticketId);
        if (histories.isEmpty()) throw new EntityNotFoundException("No history to this ticket");
        Collections.sort(histories, Comparator.comparing(History::getDate).reversed());
        return histories.stream()
                .map(historyMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @KafkaListener(topics = "historyTopic")
    public void handleHistory(HistorySaveEvent historySaveEvent) {
        log.info("Message taken in history-service ",historySaveEvent.toString());

        createNewHistory(historySaveEvent);
    }
}
