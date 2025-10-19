package com.iaraapi.service.redis;

import com.iaraapi.model.database.UserAccessType;
import com.iaraapi.repository.UserAccessTypeRepository;
import com.iaraapi.model.redis.UserAccessRedis;
import com.iaraapi.repository.redis.UserAccessRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserAccessRedisService {

    private final UserAccessTypeRepository userAccessTypeRepository;
    private final UserAccessRedisRepository userAccessRedisRepository;


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

    public List<UserAccessRedis> getCachedAccess(UUID userId) {
        String id = userId.toString();
        List<UserAccessRedis> result = new ArrayList<>();
        getCachedAccessByUserId(id, result);
        return result;
    }

    public void getCachedAccessByUserId(String userId, List<UserAccessRedis> result) {
        for (UserAccessRedis u : userAccessRedisRepository.findAll()) {
            if (u != null && u.getUserId().equals(userId)) {
                result.add(u);
            }
        }
    }
}
