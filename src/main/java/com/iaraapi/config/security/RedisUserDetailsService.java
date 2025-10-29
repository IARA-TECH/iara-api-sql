package com.iaraapi.config.security;

import com.iaraapi.model.database.UserAccessType;
import com.iaraapi.repository.UserAccessTypeRepository;
import com.iaraapi.repository.UserRepository;
import com.iaraapi.repository.redis.UserAccessRedisRepository;
import com.iaraapi.model.redis.UserAccessRedis;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import com.iaraapi.model.database.User;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RedisUserDetailsService implements UserDetailsService {

    private final UserAccessRedisRepository userAccessRedisRepository;
    private final UserAccessTypeRepository userAccessTypeRepository;
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("[RedisUserDetailsService][loadUserByUsername] Get user with email: {}", email);
        User user = userRepository.findByEmailIgnoreCase(email.trim())
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found."));

        String userId = user.getId().toString();

        List<UserAccessRedis> cachedRoles = userAccessRedisRepository.findByUserId(userId);
        if (cachedRoles == null || cachedRoles.isEmpty()) {
            log.info("User with email {} has no cached roles", email);

            List<UserAccessRedis> rolesFromDb = getRolesFromDatabase(userId);

            if (rolesFromDb.isEmpty()) {
                log.error("User with email {} has no attributed roles", email);
                throw new UsernameNotFoundException("User with " + email + " has no roles");
            }

            userAccessRedisRepository.saveAll(rolesFromDb);
            cachedRoles = rolesFromDb;
        }
        List<SimpleGrantedAuthority> authorities = getAuthorities(cachedRoles);

        log.info("User with email {} has roles {}", email, authorities);
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities);
    }

    private List<SimpleGrantedAuthority> getAuthorities(List<UserAccessRedis> cachedRoles) {
        return cachedRoles.stream()
                .filter(Objects::nonNull)
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getAccessTypeName()))
                .collect(Collectors.toList());
    }

    private List<UserAccessRedis> getRolesFromDatabase(String userId) {
        log.info("[RedisUserDetailsService] [getRolesFromDatabase] Getting all access types for user={}", userId);

        List<UserAccessType> roles = userAccessTypeRepository.findByUser_Id(UUID.fromString(userId));
        log.info("Roles lidas do DB: {}", roles.size());

        log.info("Nomes das Roles lidas do DB: {}", roles.stream().map(r -> r.getAccessType().getName()).collect(Collectors.joining(", ")));

        return roles.stream()
                .map(role -> new UserAccessRedis(
                        role.getUser().getId().toString() + "_" + role.getAccessType().getName(),
                        role.getUser().getId().toString(),
                        role.getAccessType().getId(),
                        role.getAccessType().getName()))
                .toList();
    }
}