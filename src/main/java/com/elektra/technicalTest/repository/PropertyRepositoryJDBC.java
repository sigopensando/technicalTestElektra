package com.elektra.technicalTest.repository;

import com.elektra.technicalTest.converter.PropertyRowMapper;
import com.elektra.technicalTest.entity.PropertyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class PropertyRepositoryJDBC {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<PropertyEntity> findAll() {
        return jdbcTemplate.query("call SelectAllProperties()", new PropertyRowMapper());
    }



    public PropertyEntity save(PropertyEntity propertyEntity) {
        return null;
    }

    public Optional<PropertyEntity> findById(Long propertyId) {
        return Optional.empty();
    }

    public void deleteById(Long propertyId) {
    }

}
