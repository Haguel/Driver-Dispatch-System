package dev.haguel.dds.service;

import dev.haguel.dds.DTO.AppUserDTO;
import dev.haguel.dds.DTO.SignUpDTO;
import dev.haguel.dds.exception.RoleNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {
    @Mock
    private AppUserService appUserService;

    @Mock
    private RoleService roleService;

    @Mock
    private AppUserRoleService appUserRoleService;

    @InjectMocks
    private AuthService authService;

    @Test
    @DisplayName("Should not save new user if role found")
    void shouldNot_saveNewUser_ifRoleNotFound() throws RoleNotFoundException {
        Mockito.when(roleService.getRoleByRoleName(Mockito.any())).thenThrow(new RoleNotFoundException("Role not found"));

        assertThrows(RoleNotFoundException.class, () -> authService.signUp(new SignUpDTO()));

        Mockito.verify(appUserService, Mockito.times(0)).save(Mockito.any(AppUserDTO.class));
    }
}