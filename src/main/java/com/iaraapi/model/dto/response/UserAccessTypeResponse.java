package com.iaraapi.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class UserAccessTypeResponse {
    private UUID userId;
    private Integer accessTypeId;
    private String accessTypeName;
}
