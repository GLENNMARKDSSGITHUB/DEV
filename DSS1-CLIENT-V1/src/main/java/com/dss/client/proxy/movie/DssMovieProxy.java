package com.dss.client.proxy.movie;

import com.dss.client.configuration.CustomFeignClientConfiguration;
import com.dss.client.utils.DssCommonMessageDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "${feign.movie-name}" , url ="${feign.movie-url}", configuration = CustomFeignClientConfiguration.class)
public interface DssMovieProxy {

    @PostMapping("/API/movie-catalogue/add-digistreammovie.do")
    DssCommonMessageDetails addDigiStreamMovie(@RequestBody Object obj);

    @GetMapping("/API/movie-catalogue/display-digistreammovie.do")
    DssCommonMessageDetails displayDigiStreamMovie();

    @GetMapping("/API/movie-catalogue/search-digistreammovie.do/{movieTitle}")
    DssCommonMessageDetails searchDigiStreamMovie(@PathVariable("movieTitle") String movieTitle);

    @PutMapping("/API/movie-catalogue/update-digistreammovie.do")
    DssCommonMessageDetails updateDigiStreamMovie(@RequestBody Object obj);

    @DeleteMapping("/API/movie-catalogue/delete-digistreammovie.do/{movieTitle}")
    DssCommonMessageDetails deleteDigiStreamMovie(@PathVariable("movieTitle") String movieTitle);
}
