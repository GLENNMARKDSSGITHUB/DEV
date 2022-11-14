/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.repository.movie;

import com.dss.entity.movie.DssMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This is an interface DssMovieRepository which extends the JpaRepository interface
 */

@Repository
public interface DssMovieRepository extends JpaRepository<DssMovie, String> {

    @Query(value = "SELECT * FROM DSS_MOVIE WHERE MOVIE_TITLE = :movieTitle", nativeQuery = true)
    List<DssMovie> findDssMovieByMovieTitle(@Param("movieTitle") String movieTitle);

    @Query(value = "SELECT * FROM DSS_MOVIE WHERE MOVIE_ID = :movieId", nativeQuery = true)
    List<DssMovie> findDssMovieByMovieId(@Param("movieId") String movieId);
}
