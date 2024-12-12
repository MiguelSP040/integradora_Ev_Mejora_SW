package utez.edu.mx.hospital.Floor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import utez.edu.mx.hospital.modules.Floor.Floor;
import utez.edu.mx.hospital.modules.Floor.FloorRepository;
import utez.edu.mx.hospital.modules.Floor.FloorService;
import utez.edu.mx.hospital.modules.User.User;
import utez.edu.mx.hospital.utils.CustomResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FloorServiceTest {

    @InjectMocks
    private FloorService floorService;

    @Mock
    private FloorRepository floorRepository;

    @Mock
    private CustomResponseEntity customResponseEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveFloorSuccess() {
        // Arrange
        User secretary = new User();
        secretary.setId(10L);
        Floor mockFloor = new Floor();
        mockFloor.setIdentificationName("Piso 1");
        mockFloor.setSecretary(secretary);

        when(floorRepository.save(any(Floor.class))).thenReturn(mockFloor);
        when(customResponseEntity.getOkResponse(
                eq("Registro exitoso"),
                eq("CREATED"),
                eq(201),
                isNull()))
                .thenReturn(ResponseEntity.status(201).build());

        // Act
        ResponseEntity<?> response = floorService.saveFloor(mockFloor);

        // Assert
        assertNotNull(response);
        assertEquals(201, response.getStatusCodeValue());
        verify(floorRepository, times(1)).save(mockFloor);
        verify(customResponseEntity, times(1)).getOkResponse(
                eq("Registro exitoso"),
                eq("CREATED"),
                eq(201),
                isNull()
        );
    }

    @Test
    void testUpdateFloorSuccess() {
        // Arrange
        User secretary = new User();
        secretary.setId(10L);

        Floor existingFloor = new Floor();
        existingFloor.setId(1L);
        existingFloor.setIdentificationName("Piso 1");
        existingFloor.setSecretary(secretary);

        Floor updatedFloor = new Floor();
        updatedFloor.setId(1L);
        updatedFloor.setIdentificationName("Piso 1 Actualizado");
        updatedFloor.setSecretary(secretary);

        when(floorRepository.findById(1L)).thenReturn(existingFloor);
        when(floorRepository.save(any(Floor.class))).thenReturn(updatedFloor);
        when(customResponseEntity.getOkResponse(
                eq("Actualización exitosa"),
                eq("OK"),
                eq(200),
                isNull()))
                .thenReturn(ResponseEntity.ok().build());

        // Act
        ResponseEntity<?> response = floorService.updateFloor(updatedFloor);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        verify(floorRepository, times(1)).findById(1L);
        verify(floorRepository, times(1)).save(updatedFloor);
        verify(customResponseEntity, times(1)).getOkResponse(
                eq("Actualización exitosa"),
                eq("OK"),
                eq(200),
                isNull()
        );
    }
}
