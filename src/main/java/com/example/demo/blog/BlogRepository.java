package com.example.demo.blog;

import com.example.demo.models.Blog;
import com.example.demo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository  extends JpaRepository<Blog, Integer> {

    @Query(value = "SELECT COUNT(*) FROM Blog b WHERE " +
            " b.created_by = :username and b.created_at = current_date",
            nativeQuery = true)
    public int countTodayBlogsByUser(String username);

    public List<Blog> findByUsername(String username);
}
