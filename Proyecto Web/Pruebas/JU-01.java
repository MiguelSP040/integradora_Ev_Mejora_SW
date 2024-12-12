package utez.edu.mx.inventario4c.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import utez.edu.mx.inventario4c.auth.DTO.AuthLoginDTO;
import utez.edu.mx.inventario4c.modules.user.User;
import utez.edu.mx.inventario4c.modules.user.UserRepository;
import utez.edu.mx.inventario4c.utils.CustomResponseEntity;
import utez.edu.mx.inventario4c.utils.security.JWTUtil;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CustomResponseEntity customResponseEntity;

    @Mock
    private JWTUtil jwtUtil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoginWithValidCredentials() {
        // Arrange
        AuthLoginDTO authLoginDTO = new AuthLoginDTO("IsaRV", "12345");

        // Mock del usuario encontrado
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setUsername("IsaRV");
        mockUser.setName("Isael");
        mockUser.setSurname("Reyes");
        mockUser.setLastname("Vargas");
        mockUser.setEmail("isael@example.com");
        mockUser.setPassword("12345");

        when(userRepository.findByPasswordAndEmailOrUsername("12345", "IsaRV"))
                .thenReturn(mockUser);

        // Mock del token JWT generado
        String mockToken = "mockJwtToken";
        when(jwtUtil.generateToken(any())).thenReturn(mockToken);

        // Cuerpo de la respuesta
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("token", mockToken);
        responseBody.put("user", Map.of(
                "id", mockUser.getId(),
                "username", mockUser.getUsername(),
                "name", mockUser.getName(),
                "surname", mockUser.getSurname(),
                "lastname", mockUser.getLastname(),
                "email", mockUser.getEmail()
        ));

        // Solución: Crear un ResponseEntity explícito
        ResponseEntity<Map<String, Object>> mockResponse = ResponseEntity.ok(responseBody);

        // Ajuste del mock para el método
        when(customResponseEntity.getOkResponse(
                eq("Inicio de sesión exitoso"),
                eq("OK"),
                eq(200),
                eq(responseBody))
        ).thenReturn((ResponseEntity) mockResponse);

        // Act
        ResponseEntity<?> response = authService.login(authLoginDTO);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockResponse, response);
        verify(userRepository, times(1)).findByPasswordAndEmailOrUsername("12345", "IsaRV");
        verify(jwtUtil, times(1)).generateToken(any());
        verify(customResponseEntity, times(1)).getOkResponse(
                eq("Inicio de sesión exitoso"),
                eq("OK"),
                eq(200),
                eq(responseBody)
        );
    }
}
