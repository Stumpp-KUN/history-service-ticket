package com.example.historyservice.mapper;

import com.example.historyservice.dto.HistoryDTO;
import com.example.historyservice.entity.History;
import com.example.historyservice.event.HistorySaveEvent;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface HistoryMapper {

    History mapToHistory(HistorySaveEvent historySaveEvent);

    HistorySaveEvent mapToHistorySaveEvent(History history);

    History mapToEntity(HistoryDTO historyDTO);

    HistoryDTO mapFromEntity(History history);
}

