package com.example.historyservice.service;

import com.example.historyservice.dto.HistoryDTO;
import com.example.historyservice.entity.History;
import com.example.historyservice.event.HistorySaveEvent;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface HistoryService {
    List<HistoryDTO> getAllHistory(Long ticketId) throws EntityNotFoundException;
    HistoryDTO createNewHistory(HistorySaveEvent historySaveEvent);
}
