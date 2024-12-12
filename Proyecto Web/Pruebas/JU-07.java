package utez.edu.mx.hospital.Bed;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import utez.edu.mx.hospital.modules.Bed.Bed;
import utez.edu.mx.hospital.modules.Bed.BedRepository;
import utez.edu.mx.hospital.modules.Bed.BedService;
import utez.edu.mx.hospital.modules.Floor.Floor;
import utez.edu.mx.hospital.utils.CustomResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BedServiceTest {

    @InjectMocks
    private BedService bedService;

    @Mock
    private BedRepository bedRepository;

    @Mock
    private CustomResponseEntity customResponseEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveBedSuccess() {
        // Arrange
        Floor floor = new Floor();
        floor.setId(1L);
        floor.setIdentificationName("Piso 1");

        Bed mockBed = new Bed();
        mockBed.setIdentificationName("CAMA-01");
        mockBed.setFloor(floor);
        mockBed.setIsOccupied(false);
        mockBed.setHasNurse(false);

        when(bedRepository.save(any(Bed.class))).thenReturn(mockBed);
        when(customResponseEntity.getOkResponse(
                eq("Registro exitoso"),
                eq("CREATED"),
                eq(201),
                isNull()))
                .thenReturn(ResponseEntity.status(201).build());

        // Act
        ResponseEntity<?> response = bedService.save(mockBed);

        // Assert
        assertNotNull(response);
        assertEquals(201, response.getStatusCodeValue());
        verify(bedRepository, times(1)).save(mockBed);
        verify(customResponseEntity, times(1)).getOkResponse(
                eq("Registro exitoso"),
                eq("CREATED"),
                eq(201),
                isNull()
        );
    }

    @Test
    void testUpdateBedSuccess() {
        // Arrange
        Floor floor = new Floor();
        floor.setId(2L);
        floor.setIdentificationName("Piso 2");

        Bed existingBed = new Bed();
        existingBed.setId(1L);
        existingBed.setIdentificationName("CAMA-01");
        existingBed.setFloor(floor);
        existingBed.setIsOccupied(false);
        existingBed.setHasNurse(false);

        Bed updatedBed = new Bed();
        updatedBed.setId(1L);
        updatedBed.setIdentificationName("CAMA-01");
        updatedBed.setFloor(floor);
        updatedBed.setIsOccupied(false);
        updatedBed.setHasNurse(false);

        when(bedRepository.findById(1L)).thenReturn(existingBed);
        when(bedRepository.save(any(Bed.class))).thenReturn(updatedBed);
        when(customResponseEntity.getOkResponse(
                eq("Actualizacion exitosa"),
                eq("Actualizado"),
                eq(200),
                isNull()))
                .thenReturn(ResponseEntity.ok().build());

        // Act
        ResponseEntity<?> response = bedService.update(updatedBed);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        verify(bedRepository, times(1)).findById(1L);
        verify(bedRepository, times(1)).save(updatedBed);
        verify(customResponseEntity, times(1)).getOkResponse(
                eq("Actualizacion exitosa"),
                eq("Actualizado"),
                eq(200),
                isNull()
        );
    }
}
