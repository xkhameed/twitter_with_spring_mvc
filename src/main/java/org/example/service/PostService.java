package org.example.service;

import org.example.config.security.SessionUser;
import org.example.dao.PostDao;
import org.example.entity.AuthUser;
import org.example.entity.Post;
import org.example.enums.GeneralStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class PostService {


    private final SessionUser sessionUser;
    private final PostDao postDao;

    public PostService(SessionUser sessionUser, PostDao postDao) {
        this.sessionUser = sessionUser;
        this.postDao = postDao;
    }

    public void createPost(String title , String content ){

        AuthUser authUser = sessionUser.getAuthUser();
        if ( authUser.getStatus().equals(GeneralStatus.BLOCK) ) return;

        Post post = Post.builder()
                .title(title)
                .content(content)
                .userId(authUser.getId())
                .createdTime(LocalDateTime.now().toString())
                .build();
        postDao.save(post);
    }


    public List<Post> getAll(){
        return postDao.findAll();
    }


}
