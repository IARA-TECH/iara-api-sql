package com.iaraapi.dto.response;

import com.iaraapi.model.UserAccessType;
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
