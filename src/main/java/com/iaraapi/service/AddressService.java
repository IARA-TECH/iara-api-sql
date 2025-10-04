package com.iaraapi.service;

import com.iaraapi.dto.request.AddressRequest;
import com.iaraapi.dto.response.AddressResponse;
import com.iaraapi.mapper.AddressMapper;
import com.iaraapi.model.Address;
import com.iaraapi.repository.AddressRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class AddressService extends BaseService<Address, Long, AddressRequest, AddressResponse> {
    private AddressMapper mapper;

    public AddressService(AddressRepository repository, AddressMapper mapper) {
        super(repository, "Address");
        this.mapper = mapper;
    }

    @Override
    protected Address toEntity(AddressRequest request) {
        return mapper.toEntity(request);
    }

    @Override
    protected AddressResponse toResponse(Address address) {
        return mapper.toResponse(address, address.getFactory().getName());
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
    public AddressResponse deactivateEntity(Long id) {
        log.info("[AddressService] [deactivateEntity] DEACTIVATE ACCESS TYPE WITH ID {}", id);
        Address address = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address with ID " + id + " not found."));

        address.setDeactivatedAt(LocalDateTime.now());
        repository.save(address);
        return toResponse(address);
    }

    @Override
    public AddressResponse reactivateEntity(Long id) {
        log.info("[AddressService] [reactivateEntity] DEACTIVATE ACCESS TYPE WITH ID {}", id);
        Address address = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address with ID " + id + " not found."));

        address.setDeactivatedAt(null);
        repository.save(address);
        return toResponse(address);
    }
}


