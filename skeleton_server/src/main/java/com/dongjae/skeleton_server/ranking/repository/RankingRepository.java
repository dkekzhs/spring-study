package com.dongjae.skeleton_server.ranking.repository;

import com.dongjae.skeleton_server.ranking.domain.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RankingRepository extends JpaRepository<Ranking, Long> {

    @Query(value = "SELECT M.name as userName, R.score as score, " +
            "ROW_NUMBER() OVER (ORDER BY r.score DESC) as rank " +
            "FROM RANKING R " +
            "INNER JOIN MEMBER M ON R.user_id = M.id " +
            "ORDER BY R.score DESC " +
            "LIMIT :limit OFFSET :offset",
            nativeQuery = true)
    List<Object[]> findTopRankings(@Param("limit") int limit, @Param("offset") int offset);

}
