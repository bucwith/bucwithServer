package com.bucwith.repository.community;

import com.bucwith.domain.comment.Comment;
import com.bucwith.domain.community.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommunityRepository extends JpaRepository<Community, Long> {
    @Query("SELECT c FROM Community c ORDER BY c.commuId DESC")
    List<Community> findAllDesc();

    @Query("SELECT c FROM Community c WHERE c.user.userId = :userId ORDER BY c.commuId desc ")
    List<Community> findMyCommunities(@Param("userId") Long userId);

    @Modifying
    @Query("update Community c set c.viewCnt = c.viewCnt + 1 where c.commuId = :commuId")
    int updateView(Long commuId);

    @Modifying
    @Query("update Community c set c.commentCnt = c.commentCnt + 1 where c.commuId = :commuId")
    int updatePlusComment(Long commuId);

    @Modifying
    @Query("update Community c set c.commentCnt = c.commentCnt - 1 where c.commuId = :commuId")
    int updateMinusComment(Long commuId);

    @Modifying
    @Query("update Community c set c.likeCnt = c.likeCnt + 1 where c.commuId = :commuId")
    int updatePlusLike(Long commuId);

    @Modifying
    @Query("update Community c set c.likeCnt = c.likeCnt - 1 where c.commuId = :commuId")
    int updateMinusLike(Long commuId);
}
