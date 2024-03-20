package com.elektra.technicalTest.converter;

import com.elektra.technicalTest.dto.CurrenciesDTO;
import com.elektra.technicalTest.dto.PropertyDTO;
import com.elektra.technicalTest.entity.PropertyEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class PropertyConverter {

    final String url = "https://api.freecurrencyapi.com/v1/latest?apikey=fca_live_v3PjAjuYIBRjuc8AA6bNJQkcwc9lyjffSOrQgh23";

    public PropertyEntity convertDTOToEntity(PropertyDTO propertyDTO) {

        PropertyEntity propertyEntity = new PropertyEntity();
        propertyEntity.setTitle(propertyDTO.getTitle());
        propertyEntity.setDescription(propertyDTO.getDescription());
        propertyEntity.setOwnerName(propertyDTO.getOwnerName());
        propertyEntity.setOwnerEmail(propertyDTO.getOwnerEmail());
        propertyEntity.setPrice(propertyDTO.getPrice());
        propertyEntity.setAddress(propertyDTO.getAddress());

        return propertyEntity;
    }

    public PropertyDTO convertEntityToDTO(PropertyEntity propertyEntity) {

        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setId(propertyEntity.getId());
        propertyDTO.setTitle(propertyEntity.getTitle());
        propertyDTO.setDescription(propertyEntity.getDescription());
        propertyDTO.setOwnerName(propertyEntity.getOwnerName());
        propertyDTO.setOwnerEmail(propertyEntity.getOwnerEmail());
        propertyDTO.setPrice(propertyEntity.getPrice());
        propertyDTO.setAddress(propertyEntity.getAddress());

        Double mxn = 0.0;
        try {
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(url, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            CurrenciesDTO currenciesDTO = objectMapper.readValue(response, CurrenciesDTO.class);

            mxn = currenciesDTO.getData().get("MXN");

        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }

        propertyDTO.setPriceMx(propertyEntity.getPrice() * mxn);

        return propertyDTO;
    }

}
