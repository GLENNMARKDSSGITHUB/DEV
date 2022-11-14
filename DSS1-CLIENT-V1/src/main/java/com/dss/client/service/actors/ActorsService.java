package com.dss.client.service.actors;

import java.util.List;

public interface ActorsService {

    String addActor(Object obj);

    List<Object> displayActors();

    List<Object> searchActorByActorName(String firstName, String lastName);

    String updateActor(Object obj);

    String deleteActor(String firstName, String lastName);
}
