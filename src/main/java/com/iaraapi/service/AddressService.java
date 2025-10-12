package com.iaraapi.service;

import com.iaraapi.model.dto.request.AddressRequest;
import com.iaraapi.model.dto.response.AddressResponse;
import com.iaraapi.model.mapper.AddressMapper;
import com.iaraapi.model.database.Address;
import com.iaraapi.model.database.Factory;
import com.iaraapi.repository.AddressRepository;
import com.iaraapi.repository.FactoryRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class AddressService extends BaseService<Address, Integer, AddressRequest, AddressResponse> {
    private final AddressMapper mapper;
    private final FactoryRepository factoryRepository;

    public AddressService(AddressRepository repository, AddressMapper mapper, FactoryRepository factoryRepository) {
        super(repository, "Address");
        this.mapper = mapper;
        this.factoryRepository = factoryRepository;
    }

    @Override
    protected Address toEntity(AddressRequest request) {
        Factory factory = factoryRepository.findById(request.getFactoryId())
                .orElseThrow(() -> new EntityNotFoundException("Factory with id" + request.getFactoryId() + " not found"));
        return mapper.toEntity(request, factory);
    }

    @Override
    protected AddressResponse toResponse(Address address) {
        return mapper.toResponse(address);
    }

    @Override
    protected void updateEntity(Address address, AddressRequest request) {
        address.setState(request.getState());
        address.setCity(request.getCity());
        address.setNeighborhood(request.getNeighborhood());
        address.setCep(request.getCep());
        address.setBuildingNumber(request.getBuildingNumber());
        address.setStreet(request.getStreet());
        address.setComplement(request.getComplement());
    }

    @Override
    @Transactional
    public AddressResponse deactivateEntity(Integer id) {
        log.info("[AddressService] [deactivateEntity] DEACTIVATE ADDRESS WITH ID {}", id);
        Address address = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address with ID " + id + " not found."));

        address.setDeactivatedAt(LocalDateTime.now());
        repository.save(address);
        return toResponse(address);
    }

    @Override
    @Transactional
    public AddressResponse reactivateEntity(Integer id) {
        log.info("[AddressService] [reactivateEntity] DEACTIVATE ADDRESS WITH ID {}", id);
        Address address = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address with ID " + id + " not found."));

        address.setDeactivatedAt(null);
        repository.save(address);
        return toResponse(address);
    }
}


