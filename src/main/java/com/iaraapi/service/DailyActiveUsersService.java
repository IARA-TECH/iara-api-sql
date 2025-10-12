package com.iaraapi.service;

import com.iaraapi.model.dto.request.DailyActiveUsersRequest;
import com.iaraapi.model.dto.response.DailyActiveUsersResponse;
import com.iaraapi.model.mapper.DailyActiveUsersMapper;
import com.iaraapi.repository.DailyActiveUsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DailyActiveUsersService {
    private final DailyActiveUsersRepository repository;
    private final DailyActiveUsersMapper mapper;

    @Transactional
    public void create(DailyActiveUsersRequest request) {
        repository.createDailyActiveUsers(request.getUserId());

    }

    public List<DailyActiveUsersResponse> getAll(){
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}
