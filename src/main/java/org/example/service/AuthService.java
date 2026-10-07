package org.example.service;

import org.example.dao.AuthUserDao;
import org.example.dto.RegisterDTO;
import org.example.entity.AuthUser;
import org.example.enums.GeneralStatus;
import org.example.enums.Role;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthService {

    private final AuthUserDao authUserDao;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AuthUserDao authUserDao, PasswordEncoder passwordEncoder) {
        this.authUserDao = authUserDao;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterDTO dto){
        Optional<AuthUser> optional = authUserDao.findByUsername(dto.username());
        if (optional.isPresent()) return;
        AuthUser authUser = AuthUser.builder()
                .name(dto.name())
                .username(dto.username())
                .password( passwordEncoder.encode(dto.password()) )
                .role(Role.ROLE_USER)
                .status(GeneralStatus.ACTIVE)
                .visible(true)
                .build();
        authUserDao.save(authUser);
    }

}
