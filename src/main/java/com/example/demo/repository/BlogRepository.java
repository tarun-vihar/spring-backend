package com.example.demo.repository;

import com.example.demo.models.entity.Blog;
import com.example.demo.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long> {

    @Query(value = "SELECT COUNT(*) FROM Blog b WHERE " +
            " b.published_by = :username and b.created_at = current_date",
            nativeQuery = true)
    public int countTodayBlogsByUser(User username);

    public List<Blog> findByUser(User username);

    /*

    @Query(value = "WITH CTE AS (\n" +
            " \tselect blog_id from comment \n" +
            "\tGROUP BY blog_id\n" +
            "\tHAVING COUNT(DISTINCT(SENTIMENT)) = 1\n" +
            "),\n" +
            "CTE2 AS (\n" +
            "\tselect DISTINCT(cte.blog_id) from CTE\n" +
            "\tINNER JOIN COMMENT com\n" +
            "\tON com.blog_id = cte.blog_id where com.sentiment = 'like'\n" +
            ") \n" +
            "select * from blog where blog_id in ( select blog_id from CTE2) and  published_by = :user",
            nativeQuery = true)
    public List<Blog> getPositveCommentedBlogs(User user);


     */



    @Query(value = "SELECT * FROM Blog b WHERE " +
            " b.published_by = :username and blog_id in (select blog_id from comment where sentiment = 'like') ",
            nativeQuery = true)
    public List<Blog> someNegativeComments(User username);
}
