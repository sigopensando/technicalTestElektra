package com.elektra.technicalTest.repository;

import com.elektra.technicalTest.entity.PropertyEntity;
import org.springframework.data.repository.CrudRepository;

public interface PropertyRepository extends CrudRepository<PropertyEntity, Long> {
}
