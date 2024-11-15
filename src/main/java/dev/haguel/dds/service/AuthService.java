package dev.haguel.dds.service;

import dev.haguel.dds.DTO.AppUserDTO;
import dev.haguel.dds.DTO.SignUpDTO;
import dev.haguel.dds.enumeration.Roles;
import dev.haguel.dds.exception.RoleNotFoundException;
import dev.haguel.dds.model.AppUser;
import dev.haguel.dds.model.Role;
import dev.haguel.dds.util.PasswordEncrypter;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class AuthService {
    private final AppUserService appUserService;
    private final RoleService roleService;
    private final AppUserRoleService appUserRoleService;

    public void signUp(SignUpDTO signUpDTO) throws RoleNotFoundException, DataIntegrityViolationException {
        Role role = roleService.getRoleByRoleName(Roles.ROLE_DISPATCHER);
        String passwordHash = PasswordEncrypter.encryptPassword(signUpDTO.getPassword());
        AppUser appUser = appUserService.save(new AppUserDTO(signUpDTO.getUsername(), passwordHash));

        appUserRoleService.save(appUser, role);
    }
}
