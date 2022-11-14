/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.controller.movie;

import com.dss.dto.movie.DssMovieDTO;
import com.dss.service.movie.DssMovieService;
import com.dss.util.exception.MovieNotExistingException;
import com.dss.util.utils.DssCommonMessageDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * This class is a controller layer for DSS Movies
 */

@RestController
@RequestMapping("/API/movie-catalogue")
public class DssMovieController {

    @Autowired
    private DssMovieService dssMovieService;

    /** Returns a DssCommonMessageDetails object if the admin user successfully adds the movie or not.
     * @param movieDto movieDto
     * @return String
     * @see #addDigiStreamMovie(DssMovieDTO)
     */
    @PostMapping("/add-digistreammovie.do")
    public DssCommonMessageDetails addDigiStreamMovie(@RequestBody DssMovieDTO movieDto){
        return dssMovieService.addDssMovie(movieDto);
    }

    /** Returns a DssCommonMessageDetails object
     * @return List<DssMovie>
     * @see #displayDigiStreamMovie()
     */
    @GetMapping("/display-digistreammovie.do")
    public DssCommonMessageDetails displayDigiStreamMovie(){
        return dssMovieService.displayDssMovies();
    }

    /** Returns a DssCommonMessageDetails object
     * @return List<DssMovie>
     * @see #searchDigiStreamMovie(String)
     */
    @GetMapping("/search-digistreammovie.do/{movieTitle}")
    public DssCommonMessageDetails searchDigiStreamMovie(@PathVariable("movieTitle") String movieTitle) throws MovieNotExistingException {
        return dssMovieService.searchDssMovieByMovieTitle(movieTitle);
    }

    /** Returns a DssCommonMessageDetails object if the admin user successfully updates the movie or not.
     * @param movieDto movieDto
     * @return String
     * @see #updateDigiStreamMovie(DssMovieDTO)
     */
    @PutMapping("/update-digistreammovie.do")
    public DssCommonMessageDetails updateDigiStreamMovie(@RequestBody DssMovieDTO movieDto) throws MovieNotExistingException {
        return dssMovieService.updateDssMovie(movieDto);
    }

    /** Returns a DssCommonMessageDetails object if the admin user successfully deletes the movie or not.
     * @param movieTitle movieTitle
     * @return String
     * @see #deleteDigiStreamMovie(String)
     */
    @DeleteMapping("/delete-digistreammovie.do/{movieTitle}")
    public DssCommonMessageDetails deleteDigiStreamMovie(@PathVariable("movieTitle") String movieTitle) throws MovieNotExistingException {
        return dssMovieService.deleteDssMovie(movieTitle);
    }
}
