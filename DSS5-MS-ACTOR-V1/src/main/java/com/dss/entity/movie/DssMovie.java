/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.entity.movie;

import com.dss.entity.actors.Actors;
import com.dss.entity.reviews.Reviews;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * This is an Entity Class for DssMovie
 */

@Entity
@Table(name = "DSS_MOVIE")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DssMovie {

    @Id
    @Column(name = "MOVIE_ID", length = 10, nullable = false)
    private String movieId;

    @Column(name = "MOVIE_TITLE", nullable = false)
    private String movieTitle;

    @Column(name = "YEAR", length = 4, nullable = false)
    private String year;

    @Column(name = "WRITERS", nullable = false)
    private String writers;

    @Column(name = "DIRECTED_BY", nullable = false)
    private String directedBy;

    @Column(name = "PRODUCED_BY", nullable = false)
    private String producedBy;

    @Column(name = "MUSIC_BY", nullable = false)
    private String musicBy;

    @Column(name = "DURATION", length = 15, nullable = false)
    private String duration;

    @Column(name = "MOVIE_COST", nullable = false)
    private double movieCost;

    @Column(name = "CATEGORY", nullable = false)
    private String category;

    @Column(name = "COUNTRY", length = 100, nullable = false)
    private String country;

    @Column(name = "LANGUAGE", length = 25, nullable = false)
    private String language;

    @Column(name = "IMAGE", length = 25, nullable = false)
    private String image;

    @Column(name = "CREATION_DATE")
    private Date creationDate;

    @Column(name = "CREATED_BY", length = 100)
    private String createdBy;

    @Column(name = "LAST_MODIFICATION_DATE")
    private Date lastModificationDate;

    @Column(name = "LAST_MODIFIED_BY", length = 100)
    private String lastModifiedBy;

    @OneToMany(mappedBy = "dss")
    @ToString.Exclude
    private List<Actors> movieActors;

    @OneToMany(mappedBy = "dss")
    @ToString.Exclude
    private List<Reviews> movieReviews;

    public List<Actors> getMovieActors() {
        return movieActors;
    }
}
