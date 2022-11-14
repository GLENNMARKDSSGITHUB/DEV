package com.dss.client.service.actors;

import com.dss.client.proxy.actors.ActorsProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorsServiceImpl implements ActorsService{

    @Autowired
    ActorsProxy actorsProxy;

    @Override
    public String addActor(Object obj) {
        return actorsProxy.addActor(obj);
    }

    @Override
    public List<Object> displayActors() {
        return actorsProxy.displayActors();
    }

    @Override
    public List<Object> searchActorByActorName(String firstName, String lastName) {
        return actorsProxy.searchActorByActorName(firstName, lastName);
    }

    @Override
    public String updateActor(Object obj) {
        return actorsProxy.updateActor(obj);
    }

    @Override
    public String deleteActor(String firstName, String lastName) {
        return actorsProxy.deleteActor(firstName, lastName);
    }
}
