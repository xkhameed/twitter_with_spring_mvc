package org.example.dao;

import org.example.entity.Post;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostDao {


    private final JdbcTemplate jdbcTemplate;

    public PostDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Post post) {
        String sql = "insert into post ( title, content, user_id, created_time ) values (?,?,?,?);";
        jdbcTemplate.update(sql, post.getTitle(), post.getContent(), post.getUserId(), post.getCreatedTime().toString());
    }


    public List<Post> findAll(){
        String sql = "select * from post;";
        return jdbcTemplate.query( sql, BeanPropertyRowMapper.newInstance(Post.class));
    }



}
