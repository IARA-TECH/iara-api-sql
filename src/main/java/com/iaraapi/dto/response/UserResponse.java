package com.iaraapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Date;

@Getter
@AllArgsConstructor
public class UserResponse {
    private String name;

    private String email;

}
