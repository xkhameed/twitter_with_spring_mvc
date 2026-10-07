package org.example.dao;


import org.example.entity.AuthUser;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthUserDao {


    private final JdbcTemplate jdbcTemplate;

    public AuthUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Optional<AuthUser> findByUsername(String username){
        String sql = "select * from auth_user where username = ?;";
        try {
            AuthUser authUser = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(AuthUser.class), username);
            return Optional.of(authUser);
        } catch ( EmptyResultDataAccessException e){
            e.printStackTrace();
            return Optional.empty();
        }
    }


    public void save(AuthUser user) {
        String sql = "insert into auth_user (name, username, password, visible, role, status) values (?,?,?,?,?,?);";
        jdbcTemplate.update( sql, user.getName(), user.getUsername(), user.getPassword(), user.getVisible(), user.getRole().toString(), user.getStatus().toString());
    }


}
