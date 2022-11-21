package com.dss.client.service.movie;

import com.dss.client.proxy.movie.DssMovieProxy;
import com.dss.client.utils.DssCommonMessageDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService{

    @Autowired
    private DssMovieProxy dssMovieProxy;

    @Override
    public DssCommonMessageDetails addDigiStreamMovie(Object obj) {
        return dssMovieProxy.addDigiStreamMovie(obj);
    }

    @Override
    public DssCommonMessageDetails displayDigiStreamMovie() {
        return dssMovieProxy.displayDigiStreamMovie();
    }

    @Override
    public DssCommonMessageDetails searchDigiStreamMovie(String movieTitle) {
        return dssMovieProxy.searchDigiStreamMovie(movieTitle);
    }

    @Override
    public DssCommonMessageDetails updateDigiStreamMovie(Object obj) {
        return dssMovieProxy.updateDigiStreamMovie(obj);
    }

    @Override
    public DssCommonMessageDetails deleteDigiStreamMovie(String movieTitle) {
        return dssMovieProxy.deleteDigiStreamMovie(movieTitle);
    }
}
