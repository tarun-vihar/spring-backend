package com.example.demo.repository;

import com.example.demo.models.entity.User;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {


    Optional<User> findByEmail(String email);


    @Query(value = "SELECT * FROM Users u WHERE u.username = :username and " +
            "u.password = :password",
            nativeQuery = true)
    public User authenticateUserByName(String username, String password);




//    Q2
    @Query(value = "\n" +
            "\n" +
            "WITH CTE AS(\n" +
            "\tselect published_by, COUNT(published_by) AS blog_count from blog\n" +
            "\tWHERE created_at = :date\n" +
            "\tGROUP BY published_by\n" +
            "\t\n" +
            "),\n" +
            "CTE2 AS(\n" +
            " select published_by from CTE where CTE.blog_count = (select MAX(CTE.BLOG_COUNT) from CTE)\n" +
            ")\n" +
            "select * from users  where username in (select published_by from CTE2)\n" +
            ";\n" +
            "\n",
            nativeQuery = true)
    public List<User> getMostBlogsInADay(Date date);


    @Query(value =
            "select username from users_relation where follower_id = :user1\n" +
                    "INTERSECT\n" +
                    "select username from users_relation where follower_id = :user2",
            nativeQuery = true)
    // Q3
    public List<User> findCommonUsers(User user1, User user2);

    //    Q4
    @Query(value = "SELECT * FROM Users u WHERE u.username " +
            "not in (select DISTINCT(published_by) from BLOG)",
            nativeQuery = true)
    public List<User> getUnpublishedUsers();



//    Q5
    @Query(value = "WITH CTE AS (\n" +
            " \tselect commented_by from comment \n" +
            "\tGROUP BY commented_by\n" +
            "\tHAVING COUNT(DISTINCT(SENTIMENT)) = 1\n" +
            "),\n" +
            " CTE2 AS (select com.commented_by from Comment com\n" +
            "\t where com.sentiment = 'dislike' \n" +
            "\t and\n" +
            "\t com.commented_by in (select commented_by from CTE)\n" +
            ")\n" +
            "select * from users where username in (select * from CTE2)",
            nativeQuery = true)
    public List<User> usersWithNegativeCommentedBlogs();


//    Q6
    @Query(value = "WITH CTE AS (\n" +
            " \tselect blog_id from comment \n" +
            "\tGROUP BY blog_id\n" +
            "\tHAVING COUNT(DISTINCT(SENTIMENT)) = 1\n" +
            "),\n" +
            "CTE2 AS (\n" +
            "\tselect DISTINCT(cte.blog_id) from CTE\n" +
            "\tINNER JOIN COMMENT com\n" +
            "\tON com.blog_id = cte.blog_id where com.sentiment != 'dislike'\n" +
            "),\n" +
            "CTE3 AS (\n" +
            "\tselect published_by from blog where blog_id in ( select blog_id from CTE2)\n" +
            "\t),\t\n" +
            "CTE4 AS (\n" +
            "\tselect * from Users where username in (select published_by from CTE3) \n" +
            "),\n" +
            "CTE5 AS (\n" +
            "\tselect CTE4.username, comment.sentiment, comment.blog_id from CTE4\n" +
            "JOIN BLOG\n" +
            "ON BLOG.published_by = CTE4.username\n" +
            "JOIN COMMENT \n" +
            "ON COMMENT.blog_id = BLOG.blog_id\n" +
            "),\n" +
            "CTE6 AS (\n" +
            "\tselect username from CTE5\n" +
            "GROUP BY username\n" +
            "HAVING COUNT(DISTINCT(SENTIMENT)) = 1\n" +
            ")\n" +
            "select * from users where username in (select username from CTE6)",
            nativeQuery = true)
    public List<User> usersWithNonNegativeCommentedBlogs();



}
