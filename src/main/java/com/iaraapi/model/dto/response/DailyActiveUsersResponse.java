package com.iaraapi.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class DailyActiveUsersResponse {
    private Integer id;
    private UUID userId;
    private LocalDateTime accessedOn;
}
