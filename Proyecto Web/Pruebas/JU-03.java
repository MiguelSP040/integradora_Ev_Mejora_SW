package utez.edu.mx.hospital.patient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import utez.edu.mx.hospital.modules.Patient.Patient;
import utez.edu.mx.hospital.modules.Patient.PatientRepository;
import utez.edu.mx.hospital.modules.Patient.PatientService;
import utez.edu.mx.hospital.utils.CustomResponseEntity;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PatientServiceTest {

    @InjectMocks
    private PatientService patientService;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private CustomResponseEntity customResponseEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSavePatientSuccess() {
        // Arrange
        Patient mockPatient = new Patient();
        mockPatient.setFullName("linette");
        mockPatient.setSurname("Sánchez");
        mockPatient.setLastname(null);
        mockPatient.setPhoneNumber("7772003939");
        mockPatient.setAssignmentDate(new Date().toString());
        mockPatient.setDischarged(false);

        when(patientRepository.save(any(Patient.class))).thenReturn(mockPatient);
        when(customResponseEntity.getOkResponse(
                eq("Paciente registrado exitosamente."),
                eq("CREATED"),
                eq(201),
                isNull()))
                .thenReturn(ResponseEntity.status(201).build());

        // Act
        ResponseEntity<?> response = patientService.savePatient(mockPatient);

        // Assert
        assertNotNull(response);
        assertEquals(201, response.getStatusCodeValue());
        verify(patientRepository, times(1)).save(mockPatient);
        verify(customResponseEntity, times(1)).getOkResponse(
                eq("Paciente registrado exitosamente."),
                eq("CREATED"),
                eq(201),
                isNull()
        );
    }

    @Test
    void testUpdatePatientSuccess() {
        // Arrange
        Patient existingPatient = new Patient();
        existingPatient.setId(1L);
        existingPatient.setFullName("linette");
        existingPatient.setSurname("Sánchez");
        existingPatient.setLastname(null);
        existingPatient.setPhoneNumber("7772003939");
        existingPatient.setAssignmentDate(new Date().toString());
        existingPatient.setDischarged(false);

        Patient updatedPatient = new Patient();
        updatedPatient.setId(1L);
        updatedPatient.setFullName("linette");
        updatedPatient.setSurname("Sánchez");
        updatedPatient.setLastname("Pérez");
        updatedPatient.setPhoneNumber("7772003939");
        updatedPatient.setAssignmentDate(existingPatient.getAssignmentDate());
        updatedPatient.setDischarged(false);

        when(patientRepository.findById(1L)).thenReturn(existingPatient);
        when(patientRepository.save(any(Patient.class))).thenReturn(updatedPatient);
        when(customResponseEntity.getOkResponse(
                eq("La información del paciente se actualizó exitosamente."),
                eq("OK"),
                eq(200),
                isNull()))
                .thenReturn(ResponseEntity.ok().build());

        // Act
        ResponseEntity<?> response = patientService.updatePatient(updatedPatient);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        verify(patientRepository, times(1)).findById(1L);
        verify(patientRepository, times(1)).save(updatedPatient);
        verify(customResponseEntity, times(1)).getOkResponse(
                eq("La información del paciente se actualizó exitosamente."),
                eq("OK"),
                eq(200),
                isNull()
        );
    }
}
