package dev.haguel.dds.service;

import dev.haguel.dds.dao.AppUserRoleRepository;
import dev.haguel.dds.model.AppUser;
import dev.haguel.dds.model.AppUserRole;
import dev.haguel.dds.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class AppUserRoleService {
    private final AppUserRoleRepository appUserRoleRepository;

    public AppUserRole save(AppUser appUser, Role role) {
        AppUserRole appUserRole = new AppUserRole(appUser, role);

        return appUserRoleRepository.save(appUserRole);
    }
}
