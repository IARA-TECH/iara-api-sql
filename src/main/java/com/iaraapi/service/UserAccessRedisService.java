package com.iaraapi.service;

import com.iaraapi.model.database.UserAccessType;
import com.iaraapi.repository.UserAccessTypeRepository;
import com.iaraapi.model.redis.UserAccessRedis;
import com.iaraapi.repository.redis.UserAccessRedisRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserAccessRedisService {

    private final UserAccessTypeRepository userAccessTypeRepository;
    private final UserAccessRedisRepository userAccessRedisRepository;

    public UserAccessRedisService(UserAccessTypeRepository userAccessTypeRepository,
                                  UserAccessRedisRepository userAccessRedisRepository) {
        this.userAccessTypeRepository = userAccessTypeRepository;
        this.userAccessRedisRepository = userAccessRedisRepository;
    }

    public void cacheUserAccess(UUID userId) {
        List<UserAccessType> accesses = userAccessTypeRepository.findByUser_Id(userId);

        accesses.forEach(access -> {
            UserAccessRedis cached = new UserAccessRedis(
                    "user:" + userId + ":access-types",
                    access.getUser().getId().toString(),
                    access.getAccessType().getId(),
                    access.getAccessType().getName()
            );
            userAccessRedisRepository.save(cached);
        });
    }

    public List<UserAccessRedis> getCachedAccess(String userId) {
        return userAccessRedisRepository.findByUserId(userId);
    }
}
