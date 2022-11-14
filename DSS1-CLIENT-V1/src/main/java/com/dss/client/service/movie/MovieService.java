package com.dss.client.service.movie;

import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface MovieService {

    String addDigiStreamMovie(@RequestBody Object obj);

    List<Object> displayDigiStreamMovie();

    List<Object> searchDigiStreamMovie(@PathVariable("movieTitle") String movieTitle);

    String updateDigiStreamMovie(@RequestBody Object obj);

    String deleteDigiStreamMovie(@PathVariable("movieTitle") String movieTitle);
}
