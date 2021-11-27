package com.example.demo.Blog;

import com.example.demo.models.Blog;
import com.example.demo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository  extends JpaRepository<Blog, Long> {


    @Query(value = "SELECT COUNT(*) FROM Blog b WHERE " +
            " b.created_by = :username and b.created_at = current_date",
            nativeQuery = true)
    public int countTodayBlogsByUser(User username);

    public List<Blog> findByUser(User username);

    @Query(value = "SELECT * FROM Blog b",
            nativeQuery = true)
    public List<Blog> getAllBlogs();
}
