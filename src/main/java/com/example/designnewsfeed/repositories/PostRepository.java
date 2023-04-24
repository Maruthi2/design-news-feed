package com.example.designnewsfeed.repositories;

import com.example.designnewsfeed.models.Post;
import com.example.designnewsfeed.services.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    @Query(value = "SELECT CASE " +
            "WHEN TIMESTAMPDIFF(SECOND, p.created_time, NOW()) < 60 THEN CONCAT(TIMESTAMPDIFF(SECOND, p.created_time, NOW()), 's ago') " +
            "WHEN TIMESTAMPDIFF(MINUTE, p.created_time, NOW()) < 60 THEN CONCAT(TIMESTAMPDIFF(MINUTE, p.created_time, NOW()), 'm ago') " +
            "WHEN TIMESTAMPDIFF(HOUR, p.created_time, NOW()) < 24 THEN CONCAT(TIMESTAMPDIFF(HOUR, p.created_time, NOW()), 'h ago') " +
            "ELSE CONCAT(TIMESTAMPDIFF(DAY, p.created_time, NOW()), 'd ago') " +
            "END AS time_ago, " +
            "p.content, " +
            "COALESCE(SUM(CASE WHEN v.type = 1 THEN 1 ELSE 0 END), 0) AS upvotes, " +
            "COALESCE(SUM(CASE WHEN v.type = 2 THEN 1 ELSE 0 END), 0) AS downvotes, " +
            "COALESCE(SUM(CASE WHEN v.type = 1 THEN 1 WHEN v.type = 2 THEN -1 ELSE 0 END), 0) AS post_score, " +
            "COALESCE(c.comment_count, 0) AS comment_count, " +
            "COALESCE(cv.comment_votes, 0) AS comment_votes " +
            "FROM post p " +
            "JOIN post_vote v ON p.id = v.post_id " +
            "JOIN user_follow uf ON uf.user2_id = p.user_id " +
            "LEFT JOIN ( " +
            "    SELECT post_id, COUNT(*) AS comment_count " +
            "    FROM comment " +
            "    WHERE comment_id IS NULL " +
            "    GROUP BY post_id " +
            ") c ON c.post_id = p.id " +
            "LEFT JOIN ( " +
            "    SELECT c.post_id, " +
            "    COALESCE(SUM(CASE WHEN v.type = 1 THEN 1 ELSE 0 END), 0) as Cupvotes, " +
            "    COALESCE(SUM(CASE WHEN v.type = 2 THEN 1 ELSE 0 END), 0) as Cdownvotes, " +
            "    COALESCE(SUM(CASE WHEN v.type = 1 THEN 1 WHEN v.type = 2 THEN -1 ELSE 0 END), 0) AS comment_votes " +
            "    FROM comment c " +
            "    JOIN comment_vote v ON c.id = v.comment_id " +
            "    WHERE c.comment_id IS  NULL " +
            "    GROUP BY c.post_id " +
            ") cv ON cv.post_id = p.id " +
            "WHERE uf.user1_id = :user1Id " +
            "GROUP BY p.id, c.comment_count, cv.comment_votes, p.created_time " +
            "ORDER BY (upvotes - downvotes) DESC, comment_count DESC, comment_votes DESC, p.created_time DESC", nativeQuery = true)
    List<?> findFeedsByUser1Id(@Param("user1Id") Long user1Id);

}
