package com.dss.client.controller.actors;

import com.dss.client.service.actors.ActorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/API/actor")
public class ActorsController {

    @Autowired
    private ActorsService actorsService;

    @PostMapping("/add-actor.do")
    String addActor(@RequestBody Object obj){
        return actorsService.addActor(obj);
    }

    @GetMapping("/display-actors.do")
    List<Object> displayActors(){
        return actorsService.displayActors();
    }

    @GetMapping("/search-actor.do/{firstName}/{lastName}")
    List<Object> searchActorByActorName(@PathVariable("firstName") String firstName,
                                           @PathVariable("lastName") String lastName){
        return actorsService.searchActorByActorName(firstName, lastName);
    }

    @PutMapping("/update-actor.do")
    String updateActor(@RequestBody Object obj){
        return actorsService.updateActor(obj);
    }

    @DeleteMapping("/delete-actor.do/{firstName}/{lastName}")
    String deleteActor(@PathVariable("firstName") String firstName,
                       @PathVariable("lastName") String lastName){
        return actorsService.deleteActor(firstName, lastName);
    }
}
