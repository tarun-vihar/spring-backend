package com.example.demo.repository;


import com.example.demo.models.entity.Blog;
import com.example.demo.models.entity.Comment;
import com.example.demo.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    public List<Comment> findByUser(User username);

    @Query(value = "SELECT COUNT(*) FROM Comment c WHERE  " +
            "c.commented_by = :user  and c.blog_id = :blog ",
            nativeQuery = true)
     public int countBlogCommentByUser(User user, Blog blog);
//



        @Query(value = "SELECT COUNT(*) FROM Comment c WHERE  " +
            "c.commented_by = :user and c.commented_at = current_date",
            nativeQuery = true)
      public int  countTodayCommentsByUser(User user);

}
