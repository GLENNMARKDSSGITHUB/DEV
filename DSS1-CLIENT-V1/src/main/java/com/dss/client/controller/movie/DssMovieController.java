package com.dss.client.controller.movie;

import com.dss.client.service.movie.MovieService;
import com.dss.client.utils.DssCommonMessageDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/API/movie-catalogue")
public class DssMovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/add-digistreammovie.do")
    public DssCommonMessageDetails addDigiStreamMovie(@RequestBody Object obj){
        return movieService.addDigiStreamMovie(obj);
    }

    @GetMapping("/display-digistreammovie.do")
    public DssCommonMessageDetails displayDigiStreamMovie(){
        return movieService.displayDigiStreamMovie();
    }

    @GetMapping("search-digistreammovie.do/{movieTitle}")
    public DssCommonMessageDetails searchDigiStreamMovie(@PathVariable("movieTitle") String movieTitle){
        return movieService.searchDigiStreamMovie(movieTitle);
    }

    @PutMapping("/update-digistreammovie.do")
    public DssCommonMessageDetails updateDigiStreamMovie(@RequestBody Object obj){
        return movieService.updateDigiStreamMovie(obj);
    }

    @DeleteMapping("/delete-digistreammovie.do/{movieTitle}")
    public DssCommonMessageDetails deleteDigiStreamMovie(@PathVariable("movieTitle") String movieTitle){
        return movieService.deleteDigiStreamMovie(movieTitle);
    }
}
