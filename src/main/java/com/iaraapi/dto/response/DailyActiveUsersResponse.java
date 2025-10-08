package com.iaraapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class DailyActiveUsersResponse {
    Integer id;
    UUID userId;
    LocalDateTime accessedOn;
}
