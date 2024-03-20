package com.elektra.technicalTest.repository;

import com.elektra.technicalTest.entity.PropertyEntity;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.result.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class PropertyRepositoryJDBC {

/*
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public PropertyEntity save(PropertyEntity propertyEntity) {
        return null;
    }

    public List<PropertyEntity> findAll() {
        return jdbcTemplate.query("call SelectAllProperties()", new );
    }

    private static class PropertyRowMapper implements RowMapper<PropertyEntity> {
        @Override
        public PropertyEntity mapRow(Resultset rs, int rowNum) {
            PropertyEntity propertyEntity = new PropertyEntity();
            propertyEntity.setId(rs.getLong("id"));
            propertyEntity.setDescription(rs.getString("description"));
            propertyEntity.setTitle(rs.get("title"));
            return null;
        }
    }

    public Optional<PropertyEntity> findById(Long propertyId) {
    }

    public void deleteById(Long propertyId) {
    }
*/
}
