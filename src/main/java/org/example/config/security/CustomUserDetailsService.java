package org.example.config.security;

import lombok.NonNull;
import org.example.dao.AuthUserDao;
import org.example.entity.AuthUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {


    private final AuthUserDao authUserDao;

    public CustomUserDetailsService(AuthUserDao authUserDao) {
        this.authUserDao = authUserDao;
    }

    @Override
    public UserDetails loadUserByUsername( @NonNull String username) throws UsernameNotFoundException {

        Optional<AuthUser> optional = authUserDao.findByUsername(username);
        AuthUser authUser = optional.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new CustomUserDetails(authUser);
    }


}
