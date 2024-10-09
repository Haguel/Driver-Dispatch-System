package dev.haguel.dds.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(exclude = {"appUser", "role"})
@ToString(exclude = {"appUser", "role"})
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "app_user_role")
public class AppUserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="app_user_id", nullable = false)
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(name="role_id", nullable = false)
    private Role role;
}
