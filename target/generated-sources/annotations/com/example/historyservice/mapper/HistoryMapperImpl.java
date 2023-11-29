package com.example.historyservice.mapper;

import com.example.historyservice.dto.HistoryDTO;
import com.example.historyservice.dto.HistoryDTO.HistoryDTOBuilder;
import com.example.historyservice.entity.History;
import com.example.historyservice.entity.History.HistoryBuilder;
import com.example.historyservice.event.HistorySaveEvent;
import com.example.historyservice.event.HistorySaveEvent.HistorySaveEventBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-28T20:01:45+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class HistoryMapperImpl implements HistoryMapper {

    @Override
    public History mapToHistory(HistorySaveEvent historySaveEvent) {
        if ( historySaveEvent == null ) {
            return null;
        }

        HistoryBuilder history = History.builder();

        history.ticketId( historySaveEvent.getTicketId() );
        history.action( historySaveEvent.getAction() );
        history.userId( historySaveEvent.getUserId() );
        history.description( historySaveEvent.getDescription() );

        return history.build();
    }

    @Override
    public HistorySaveEvent mapToHistorySaveEvent(History history) {
        if ( history == null ) {
            return null;
        }

        HistorySaveEventBuilder historySaveEvent = HistorySaveEvent.builder();

        historySaveEvent.ticketId( history.getTicketId() );
        historySaveEvent.action( history.getAction() );
        historySaveEvent.userId( history.getUserId() );
        historySaveEvent.description( history.getDescription() );

        return historySaveEvent.build();
    }

    @Override
    public History mapToEntity(HistoryDTO historyDTO) {
        if ( historyDTO == null ) {
            return null;
        }

        HistoryBuilder history = History.builder();

        history.ticketId( historyDTO.getTicketId() );
        history.date( historyDTO.getDate() );
        history.action( historyDTO.getAction() );
        history.userId( historyDTO.getUserId() );
        history.description( historyDTO.getDescription() );

        return history.build();
    }

    @Override
    public HistoryDTO mapFromEntity(History history) {
        if ( history == null ) {
            return null;
        }

        HistoryDTOBuilder historyDTO = HistoryDTO.builder();

        historyDTO.ticketId( history.getTicketId() );
        historyDTO.date( history.getDate() );
        historyDTO.action( history.getAction() );
        historyDTO.userId( history.getUserId() );
        historyDTO.description( history.getDescription() );

        return historyDTO.build();
    }
}
