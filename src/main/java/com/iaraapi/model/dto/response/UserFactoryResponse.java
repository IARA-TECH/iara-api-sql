package com.iaraapi.model.dto.response;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public interface UserFactoryResponse {
    UUID getId();
    LocalDateTime getCreatedAt();
    LocalDateTime getChangedAt();
    LocalDateTime getDeactivatedAt();
    String getName();
    String getEmail();
    Date getDateOfBirth();
    Integer getGenderId();
    String getGender();
}
