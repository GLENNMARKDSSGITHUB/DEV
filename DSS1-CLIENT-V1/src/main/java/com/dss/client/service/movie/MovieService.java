package com.dss.client.service.movie;

import com.dss.client.utils.DssCommonMessageDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface MovieService {

    DssCommonMessageDetails addDigiStreamMovie(@RequestBody Object obj);

    DssCommonMessageDetails displayDigiStreamMovie();

    DssCommonMessageDetails searchDigiStreamMovie(@PathVariable("movieTitle") String movieTitle);

    DssCommonMessageDetails updateDigiStreamMovie(@RequestBody Object obj);

    DssCommonMessageDetails deleteDigiStreamMovie(@PathVariable("movieTitle") String movieTitle);
}
