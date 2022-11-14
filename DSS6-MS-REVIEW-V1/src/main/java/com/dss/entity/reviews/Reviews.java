/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.entity.reviews;

import com.dss.entity.movie.DssMovie;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

/**
 * This is an Entity Class for Reviews
 */


@Entity
@Table(name = "DSS_REVIEWS")
@Getter
public class Reviews {

    @Id
    @Column(name = "REVIEW_ID", length = 10, nullable = false)
    private String reviewId;

    @Column(name = "MOVIE_ID", length = 10, nullable = false)
    private String movieId;

    @Column(name = "RATE", nullable = false)
    private int rate;

    @Column(name = "REVIEW_HEADLINE", nullable = false)
    private String reviewHeadline;

    @Column(name = "REVIEW_CONTENT", nullable = false)
    private String reviewContent;

    @Column(name = "CREATION_DATE", nullable = false)
    private Date creationDate;

    @Column(name = "CREATED_BY", length = 100, nullable = false)
    private String createdBy;

    @Column(name = "LAST_MODIFICATION_DATE")
    private Date lastModificationDate;

    @Column(name = "LAST_MODIFIED_BY", length = 100)
    private String lastModifiedBy;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private DssMovie dss;

    public Reviews() {
    }

    public Reviews(String reviewId, String movieId, int rate, String reviewHeadline, String reviewContent, Date creationDate, String createdBy, Date lastModificationDate, String lastModifiedBy, DssMovie dss) {
        this.reviewId = reviewId;
        this.movieId = movieId;
        this.rate = rate;
        this.reviewHeadline = reviewHeadline;
        this.reviewContent = reviewContent;
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        this.lastModificationDate = lastModificationDate;
        this.lastModifiedBy = lastModifiedBy;
        this.dss = dss;
    }

    public Reviews(String reviewId, String movieId, int rate, String reviewHeadline, String reviewContent, Date creationDate, String createdBy, Date lastModificationDate, String lastModifiedBy) {
        this.reviewId = reviewId;
        this.movieId = movieId;
        this.rate = rate;
        this.reviewHeadline = reviewHeadline;
        this.reviewContent = reviewContent;
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        this.lastModificationDate = lastModificationDate;
        this.lastModifiedBy = lastModifiedBy;
    }
}
