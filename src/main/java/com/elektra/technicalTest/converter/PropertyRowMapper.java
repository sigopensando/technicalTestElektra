package com.elektra.technicalTest.converter;

import com.elektra.technicalTest.entity.PropertyEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PropertyRowMapper implements RowMapper<PropertyEntity> {

    @Override
    public PropertyEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        PropertyEntity propertyEntity = new PropertyEntity();
        propertyEntity.setId(rs.getLong("id"));
        propertyEntity.setDescription(rs.getString("description"));
        propertyEntity.setTitle(rs.getString("title"));
        propertyEntity.setOwnerName(rs.getString("owner_name"));
        propertyEntity.setOwnerEmail(rs.getString("owner_email"));
        propertyEntity.setPrice(rs.getDouble("price"));
        propertyEntity.setAddress(rs.getString("address"));
        return propertyEntity;
    }
}

