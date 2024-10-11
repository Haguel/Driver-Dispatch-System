package dev.haguel.dds.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.haguel.dds.serializer.AppUserRoleSerializer;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = {"appUserRoles"})
@ToString(exclude = {"appUserRoles"})
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "app_user")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @JsonSerialize(using = AppUserRoleSerializer.class)
    @OneToMany(mappedBy = "appUser", fetch = FetchType.EAGER)
    private Set<AppUserRole> appUserRoles;
}
