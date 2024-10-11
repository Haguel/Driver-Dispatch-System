package dev.haguel.dds.service;

import dev.haguel.dds.DTO.AppUserDTO;
import dev.haguel.dds.dao.AppUserRepository;
import dev.haguel.dds.model.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class AppUserService {
    private final AppUserRepository appUserRepository;

    public AppUser save(AppUserDTO appUserDTO) {
        AppUser appUser = new AppUser(appUserDTO.getUsername(), appUserDTO.getPasswordHash());

        return appUserRepository.save(appUser);
    }
}
