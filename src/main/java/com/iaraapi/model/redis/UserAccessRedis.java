package com.iaraapi.model.redis;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@RedisHash(value = "userAccess", timeToLive = 3600)
@JsonIgnoreType
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccessRedis implements Serializable {
    @Id
    private String key;

    @Indexed
    private String userId;
    private Integer accessTypeId;
    private String accessTypeName;

}