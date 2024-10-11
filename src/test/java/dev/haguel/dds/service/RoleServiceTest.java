package dev.haguel.dds.service;

import dev.haguel.dds.dao.RoleRepository;
import dev.haguel.dds.enumeration.Roles;
import dev.haguel.dds.exception.RoleNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RoleServiceTest {
    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    @Test
    @DisplayName("Should throw exception when unable to find role by id")
    void should_throwException_whenUnableToFindByRoleName() {
        Mockito.when(roleRepository.findByRoleName(Mockito.any(Roles.class))).thenReturn(Optional.empty());

        assertThrows(RoleNotFoundException.class, () -> roleService.getRoleByRoleName(Roles.ROLE_ADMIN));
    }
}