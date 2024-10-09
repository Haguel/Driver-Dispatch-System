package dev.haguel.dds.dao;

import dev.haguel.dds.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);

    @Override
    default <S extends Role> S save(S entity) {
        throw new UnsupportedOperationException("Save operation is not supported for unwritable entities");
    }

    @Override
    default <S extends Role> List<S> saveAll(Iterable<S> entities) {
        throw new UnsupportedOperationException("Save operation is not supported for unwritable entities");
    }

    @Override
    default void deleteById(Long id) {
        throw new UnsupportedOperationException("Delete operation is not supported for unwritable entities");
    }

    @Override
    default void delete(Role entity) {
        throw new UnsupportedOperationException("Delete operation is not supported for unwritable entities");
    }
}
