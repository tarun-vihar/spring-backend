package com.example.demo.comment;

import com.example.demo.models.Blog;
import com.example.demo.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByBlog(int blogId);



    @Query(value = "SELECT COUNT(*) FROM Comment c WHERE  " +
            "c.username = :username  and c.blog_id = :blogId ",
            nativeQuery = true)
    public int countBlogCommentByUser(String username, int blogId);

    @Query(value = "SELECT COUNT(*) FROM Comment c WHERE  " +
            "c.username = :username and c.created_at = current_date",
            nativeQuery = true)
    public int countTodayCommentsByUser(String username);







}
