package utez.edu.mx.hospital.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import utez.edu.mx.hospital.modules.Bed.Bed;
import utez.edu.mx.hospital.modules.Bed.BedRepository;
import utez.edu.mx.hospital.modules.User.User;
import utez.edu.mx.hospital.modules.User.UserRepository;
import utez.edu.mx.hospital.modules.User.UserService;
import utez.edu.mx.hospital.utils.CustomResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BedRepository bedRepository;

    @Mock
    private CustomResponseEntity customResponseEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUserSuccess() {
        // Arrange
        User mockUser = new User();
        mockUser.setIdentificationName("Juan Pérez");
        mockUser.setUsername("juanp");
        mockUser.setEmail("juan.perez@example.com");
        mockUser.setPhoneNumber("7772001234");

        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        when(userRepository.save(any(User.class))).thenReturn(mockUser);
        when(customResponseEntity.getOkResponse(
                eq("Registro exitoso"),
                eq("OK"),
                eq(201),
                isNull()))
                .thenReturn(ResponseEntity.status(201).build());

        // Act
        ResponseEntity<?> response = userService.save(mockUser);

        // Assert
        assertNotNull(response);
        assertEquals(201, response.getStatusCodeValue());
        verify(userRepository, times(1)).save(mockUser);
        verify(customResponseEntity, times(1)).getOkResponse(
                eq("Registro exitoso"),
                eq("OK"),
                eq(201),
                isNull()
        );
    }

    @Test
    void testInsertBedNurseSuccess() {
        // Arrange
        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setIdentificationName("Juan Pérez");

        Bed bed1 = new Bed();
        bed1.setId(1L);
        bed1.setIdentificationName("CAMA-01");

        Bed bed2 = new Bed();
        bed2.setId(2L);
        bed2.setIdentificationName("CAMA-02");

        existingUser.setBeds(List.of(bed1, bed2));

        when(userRepository.findById(1L)).thenReturn(existingUser);
        when(bedRepository.findById(1L)).thenReturn(bed1);
        when(bedRepository.findById(2L)).thenReturn(bed2);
        when(customResponseEntity.getOkResponse(
                eq("Actualización exitosa"),
                eq("OK"),
                eq(200),
                isNull()))
                .thenReturn(ResponseEntity.ok().build());

        // Act
        ResponseEntity<?> response = userService.insertBedNurse(existingUser);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        verify(userRepository, times(1)).findById(1L);
        verify(bedRepository, times(1)).findById(1L);
        verify(bedRepository, times(1)).findById(2L);
        verify(customResponseEntity, times(1)).getOkResponse(
                eq("Actualización exitosa"),
                eq("OK"),
                eq(200),
                isNull()
        );
    }
}