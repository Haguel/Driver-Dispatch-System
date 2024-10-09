package dev.haguel.dds.dao;

import dev.haguel.dds.model.AppUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRoleRepository extends JpaRepository<AppUserRole, Long> {
}