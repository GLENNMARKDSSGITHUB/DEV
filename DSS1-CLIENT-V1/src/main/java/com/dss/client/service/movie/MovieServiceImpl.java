package com.dss.client.service.movie;

import com.dss.client.proxy.movie.DssMovieProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService{

    @Autowired
    private DssMovieProxy dssMovieProxy;

    @Override
    public String addDigiStreamMovie(Object obj) {
        return dssMovieProxy.addDigiStreamMovie(obj);
    }

    @Override
    public List<Object> displayDigiStreamMovie() {
        return dssMovieProxy.displayDigiStreamMovie();
    }

    @Override
    public List<Object> searchDigiStreamMovie(String movieTitle) {
        return dssMovieProxy.searchDigiStreamMovie(movieTitle);
    }

    @Override
    public String updateDigiStreamMovie(Object obj) {
        return dssMovieProxy.updateDigiStreamMovie(obj);
    }

    @Override
    public String deleteDigiStreamMovie(String movieTitle) {
        return dssMovieProxy.deleteDigiStreamMovie(movieTitle);
    }
}
