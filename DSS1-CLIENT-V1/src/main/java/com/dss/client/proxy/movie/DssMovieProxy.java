package com.dss.client.proxy.movie;

import com.dss.client.configuration.CustomFeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "${feign.movie-name}" , url ="${feign.movie-url}", configuration = CustomFeignClientConfiguration.class)
public interface DssMovieProxy {

    @PostMapping("/API/movie-catalogue/add-digistreammovie.do")
    String addDigiStreamMovie(@RequestBody Object obj);

    @GetMapping("/API/movie-catalogue/display-digistreammovie.do")
    List<Object> displayDigiStreamMovie();

    @GetMapping("/API/movie-catalogue/search-digistreammovie.do/{movieTitle}")
    List<Object> searchDigiStreamMovie(@PathVariable("movieTitle") String movieTitle);

    @PutMapping("/API/movie-catalogue/update-digistreammovie.do")
    String updateDigiStreamMovie(@RequestBody Object obj);

    @DeleteMapping("/API/movie-catalogue/delete-digistreammovie.do/{movieTitle}")
    String deleteDigiStreamMovie(@PathVariable("movieTitle") String movieTitle);
}
