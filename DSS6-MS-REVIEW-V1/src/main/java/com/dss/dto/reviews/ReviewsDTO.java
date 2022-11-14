/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.dto.reviews;

import lombok.*;

import java.util.Date;

/**
 * This class is a Data Transfer Object for ReviewsDTO
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReviewsDTO {

    private String reviewId;
    private String movieId;
    private int rate;
    private String reviewHeadline;
    private String reviewContent;
    private Date creationDate;
    private String createdBy;
    private Date lastModificationDate;
    private String lastModifiedBy;
}
