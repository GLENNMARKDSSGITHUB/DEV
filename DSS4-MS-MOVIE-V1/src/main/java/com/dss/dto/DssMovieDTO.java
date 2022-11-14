/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * This class is a Data Transfer Object for DssMovieDTO
 */
@Getter
@Setter
public class DssMovieDTO {

    private String movieId;
    private String movieTitle;
    private String year;
    private String writers;
    private String directedBy;
    private String producedBy;
    private String musicBy;
    private String duration;
    private double movieCost;
    private String category;
    private String country;
    private String language;
    private String image;
    private Date creationDate;
    private String createdBy;
    private Date lastModificationDate;
    private String lastModifiedBy;

    public DssMovieDTO(String movieId, String movieTitle, String year, String writers, String directedBy, String producedBy, String musicBy, String duration, double movieCost, String category, String country, String language, String image, Date creationDate, String createdBy, Date lastModificationDate, String lastModifiedBy) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.year = year;
        this.writers = writers;
        this.directedBy = directedBy;
        this.producedBy = producedBy;
        this.musicBy = musicBy;
        this.duration = duration;
        this.movieCost = movieCost;
        this.category = category;
        this.country = country;
        this.language = language;
        this.image = image;
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        this.lastModificationDate = lastModificationDate;
        this.lastModifiedBy = lastModifiedBy;
    }
}
