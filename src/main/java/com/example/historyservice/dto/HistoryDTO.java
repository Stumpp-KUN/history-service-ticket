package com.example.historyservice.dto;

import com.example.historyservice.enums.Action;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistoryDTO {

    private Long ticketId;

    private LocalDate date;

    private Action action;

    private Long userId;

    private String description;
}
