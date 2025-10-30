package com.iaraapi.service;

import com.iaraapi.model.dto.request.AddressRequest;
import com.iaraapi.model.dto.response.AddressResponse;
import com.iaraapi.model.mapper.AddressMapper;
import com.iaraapi.model.database.Address;
import com.iaraapi.model.database.Factory;
import com.iaraapi.repository.AddressRepository;
import com.iaraapi.repository.FactoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


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

    public String deleteAddressById(Integer id) {
        repository.deleteById(id);
        return "Address with id: " + id + " deleted";
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
}


