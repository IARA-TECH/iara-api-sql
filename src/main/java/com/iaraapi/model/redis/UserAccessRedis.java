package com.iaraapi.model.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash(value = "userAccess", timeToLive = 3600)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccessRedis implements Serializable {
    @Id
    private String key;
    private String userId;
    private Integer accessTypeId;
    private String accessTypeName;

}