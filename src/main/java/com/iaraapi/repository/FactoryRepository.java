package com.iaraapi.repository;

import com.iaraapi.model.database.Factory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactoryRepository extends JpaRepository<Factory,Integer> {
}
