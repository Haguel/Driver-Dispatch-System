package dev.haguel.dds.service;

import dev.haguel.dds.dao.RoleRepository;
import dev.haguel.dds.enumeration.Roles;
import dev.haguel.dds.exception.RoleNotFoundException;
import dev.haguel.dds.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getRoleByRoleName(Roles roleName) throws RoleNotFoundException {
        return roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new RoleNotFoundException("Role " + roleName.name() + " not found"));
    }
}
