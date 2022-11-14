package com.dss.client.controller.movie;

import com.dss.client.service.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/API/movie-catalogue")
public class DssMovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/add-digistreammovie.do")
    public String addDigiStreamMovie(@RequestBody Object obj){
        return movieService.addDigiStreamMovie(obj);
    }

    @GetMapping("/display-digistreammovie.do")
    public List<Object> displayDigiStreamMovie(){
        return movieService.displayDigiStreamMovie();
    }

    @GetMapping("search-digistreammovie.do/{movieTitle}")
    public List<Object> searchDigiStreamMovie(@PathVariable("movieTitle") String movieTitle){
        return movieService.searchDigiStreamMovie(movieTitle);
    }

    @PutMapping("/update-digistreammovie.do")
    public String updateDigiStreamMovie(@RequestBody Object obj){
        return movieService.updateDigiStreamMovie(obj);
    }

    @DeleteMapping("/delete-digistreammovie.do/{movieTitle}")
    public String deleteDigiStreamMovie(@PathVariable("movieTitle") String movieTitle){
        return movieService.deleteDigiStreamMovie(movieTitle);
    }
}
