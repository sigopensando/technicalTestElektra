package com.elektra.technicalTest.controller;

import com.elektra.technicalTest.dto.PropertyDTO;
import com.elektra.technicalTest.service.PropertyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class PropertyControllerTest {

    @InjectMocks
    private PropertyController propertyController;
    @Mock
    private PropertyService propertyService;

    PropertyDTO propertyDTOResponse;

    @BeforeEach
    void setup(){
        propertyDTOResponse = new PropertyDTO();
        propertyDTOResponse.setTitle("Apartment 1");
        propertyDTOResponse.setDescription("Apartment facing the sea");
        propertyDTOResponse.setOwnerName("Ricardo Salinas Pliego");
        propertyDTOResponse.setOwnerEmail("ricardo.salinas.pliego@gmail.com");
        propertyDTOResponse.setAddress("742 Evergreen Terrace");
        propertyDTOResponse.setPrice(750000.0);
    }

    @Test
    @DisplayName("Success scenario for saving new property")
    void testSaveProperty(){
        // Given
        PropertyDTO savedPropertyDTO = new PropertyDTO();
        savedPropertyDTO.setId(1L);
        savedPropertyDTO.setTitle(propertyDTOResponse.getTitle());

        // When
        Mockito.when(propertyService.saveProperty(propertyDTOResponse)).thenReturn(savedPropertyDTO);
        ResponseEntity<PropertyDTO> responseEntity = propertyController.saveProperty(propertyDTOResponse);

        // Then
        Assertions.assertNotNull(responseEntity.getBody().getId());
        Assertions.assertEquals(propertyDTOResponse.getTitle(), responseEntity.getBody().getTitle());
        Assertions.assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCode().value());
    }

    @Test
    @DisplayName("Success scenario for fetching all properties")
    void testGetAllProperties() {
        // Given
        List<PropertyDTO> propertyDTOsResponse = new ArrayList<>();
        propertyDTOResponse.setId(1L);
        propertyDTOsResponse.add(propertyDTOResponse);
        // When
        Mockito.when(propertyService.getAllProperties()).thenReturn(propertyDTOsResponse);
        ResponseEntity<List<PropertyDTO>> responseEntity = propertyController.getAllProperties();

        // Then
        Assertions.assertNotNull(responseEntity.getBody().get(0).getId());
        Assertions.assertEquals(propertyDTOResponse.getTitle(), responseEntity.getBody().get(0).getTitle());
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());
    }

    @Test
    @DisplayName("Success scenario for updating property")
    void testUpdateProperty() {
        // Given
        PropertyDTO updatedPropertyDTO = new PropertyDTO();
        updatedPropertyDTO.setId(1L);
        updatedPropertyDTO.setTitle(propertyDTOResponse.getTitle());
        updatedPropertyDTO.setDescription(propertyDTOResponse.getDescription());
        updatedPropertyDTO.setOwnerName(propertyDTOResponse.getOwnerName());
        updatedPropertyDTO.setOwnerEmail(propertyDTOResponse.getOwnerEmail());
        updatedPropertyDTO.setPrice(propertyDTOResponse.getPrice());

        // When
        Mockito.when(propertyService.updateProperty(propertyDTOResponse, 1L)).thenReturn(updatedPropertyDTO);
        ResponseEntity<PropertyDTO> responseEntity = propertyController.updateProperty(propertyDTOResponse, 1L);

        // Then
        Assertions.assertNotNull(responseEntity.getBody().getId());
        Assertions.assertEquals(propertyDTOResponse.getTitle(), responseEntity.getBody().getTitle());
        Assertions.assertEquals(propertyDTOResponse.getDescription(),responseEntity.getBody().getDescription());
        Assertions.assertEquals(propertyDTOResponse.getOwnerName(), responseEntity.getBody().getOwnerName());
        Assertions.assertEquals(propertyDTOResponse.getOwnerEmail(), responseEntity.getBody().getOwnerEmail());
        Assertions.assertEquals(propertyDTOResponse.getPrice(), responseEntity.getBody().getPrice());
        Assertions.assertEquals(1L, responseEntity.getBody().getId());
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());
    }

    @Test
    @DisplayName("Success scenario for updating only description of the property")
    void testUpdatePropertyDescription() {
        // Given
        PropertyDTO updatedPropertyDTO = new PropertyDTO();
        updatedPropertyDTO.setId(1L);
        updatedPropertyDTO.setDescription(propertyDTOResponse.getDescription());

        // When
        Mockito.when(propertyService.updatePropertyDescription(propertyDTOResponse, 1L)).thenReturn(updatedPropertyDTO);
        ResponseEntity<PropertyDTO> responseEntity = propertyController.updatePropertyDescription(propertyDTOResponse, 1L);

        // Then
        Assertions.assertNotNull(responseEntity.getBody().getId());
        Assertions.assertEquals(propertyDTOResponse.getDescription(), responseEntity.getBody().getDescription());
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());
    }

    @Test
    @DisplayName("Success scenario for updating only price of the property")
    void testUpdatePropertyPrice() {
        // Given
        PropertyDTO updatedPropertyDTO = new PropertyDTO();
        updatedPropertyDTO.setId(1L);
        updatedPropertyDTO.setPrice(propertyDTOResponse.getPrice());

        // When
        Mockito.when(propertyService.updatePropertyPrice(propertyDTOResponse, 1L)).thenReturn(updatedPropertyDTO);
        ResponseEntity<PropertyDTO> responseEntity = propertyController.updatePropertyPrice(propertyDTOResponse, 1L);

        // Then
        Assertions.assertNotNull(responseEntity.getBody().getId());
        Assertions.assertEquals(propertyDTOResponse.getPrice(), responseEntity.getBody().getPrice());
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());

    }

    @Test
    @DisplayName("Success scenario for deleting property")
    void testDeleteProperty() {
        // When
        Mockito.doNothing().when(propertyService).deleteProperty(1L);
        ResponseEntity<Void> responseEntity = propertyController.deleteProperty(1L);

        // Then
        Assertions.assertNull(responseEntity.getBody());
        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), responseEntity.getStatusCode().value());
    }
}
