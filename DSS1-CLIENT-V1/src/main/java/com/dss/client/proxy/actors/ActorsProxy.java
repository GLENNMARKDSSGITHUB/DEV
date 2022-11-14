package com.dss.client.proxy.actors;

import com.dss.client.configuration.CustomFeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "${feign.actor-name}" , url ="${feign.actor-url}", configuration = CustomFeignClientConfiguration.class)
public interface ActorsProxy {

    @PostMapping("/API/actor/add-actor.do")
    String addActor(@RequestBody Object obj);

    @GetMapping("/API/actor/display-actors.do")
    List<Object> displayActors();

    @GetMapping("/API/actor/search-actor.do/{firstName}/{lastName}")
    List<Object> searchActorByActorName(@PathVariable("firstName") String firstName,
                                               @PathVariable("lastName") String lastName);

    @PutMapping("/API/actor/update-actor.do")
    String updateActor(@RequestBody Object obj);

    @DeleteMapping("/API/actor/delete-actor.do/{firstName}/{lastName}")
    String deleteActor(@PathVariable("firstName") String firstName,
                              @PathVariable("lastName") String lastName);
}
