package com.iaraapi.config.security;

import com.iaraapi.repository.UserRepository;
import com.iaraapi.repository.redis.UserAccessRedisRepository;
import com.iaraapi.model.redis.UserAccessRedis;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import com.iaraapi.model.database.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RedisUserDetailsService implements UserDetailsService {

    private final UserAccessRedisRepository userAccessRedisRepository;
    private final UserRepository userRepository;

    public RedisUserDetailsService(UserAccessRedisRepository userAccessRedisRepository,
                                   UserRepository userRepository) {
        this.userAccessRedisRepository = userAccessRedisRepository;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByNameIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with name "+ username + "not found."));

        String userId = user.getId().toString();

        List<UserAccessRedis> cachedRoles = userAccessRedisRepository.findByUserId(userId);
        if (cachedRoles.isEmpty()) {
            throw new UsernameNotFoundException("User " +username+ "has no roles cached.");
        }

        List<SimpleGrantedAuthority> authorities = cachedRoles.stream()
                .map(r -> new SimpleGrantedAuthority(r.getAccessTypeName()))
                .collect(Collectors.toList());

        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }
}
