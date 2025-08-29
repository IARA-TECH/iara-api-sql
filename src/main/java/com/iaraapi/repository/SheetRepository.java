package com.iaraapi.repository;


import com.iaraapi.model.Sheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SheetRepository extends JpaRepository<Sheet, Integer> {
}
