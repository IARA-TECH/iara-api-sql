package com.iaraapi.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Date;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class UserResponse {
    private UUID id;

    private String name;

    private String email;

    private Date dateOfBirth;

    private String userManagerName;

    private UUID userManagerId;

    private String genderName;

    private Integer genderId;

    private String factoryName;

    private Integer factoryId;

    private String userPhotoUrl;
}
