package dev.haguel.dds.service;

import dev.haguel.dds.dao.AppUserRepository;
import dev.haguel.dds.dao.AppUserRoleRepository;
import dev.haguel.dds.model.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AppUserRepository appUserRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AppUser appUser = this.appUserRepository.findAppUserByUsername(userName)
                .orElseThrow(RuntimeException::new);

        if (appUser == null) {
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }

        List<String> roleNames = appUser.getAppUserRoles().stream()
                .map(appUserRole -> appUserRole.getRole().getRoleName().name())
                .toList();

        List<GrantedAuthority> grantList = new ArrayList<>();
        if (roleNames != null) {
            grantList = roleNames.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }

        return new User(appUser.getUsername(), appUser.getPassword(), grantList);
    }

}
