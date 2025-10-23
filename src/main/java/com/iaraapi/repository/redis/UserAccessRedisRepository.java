package com.iaraapi.repository.redis;

import com.iaraapi.model.redis.UserAccessRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAccessRedisRepository extends CrudRepository<UserAccessRedis, String> {
    List<UserAccessRedis> findByUserId(String userId);
}
