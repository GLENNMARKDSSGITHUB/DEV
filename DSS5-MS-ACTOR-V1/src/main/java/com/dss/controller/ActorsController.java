/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.controller;

import com.dss.dto.ActorsDTO;
import com.dss.service.ActorsService;
import com.dss.util.exception.MovieNotExistingException;
import com.dss.util.utils.DssCommonMessageDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * This class is a controller layer for DSS Actors
 */

@RestController
@RequestMapping("/API/actor")
public class ActorsController {

    @Autowired
    private ActorsService actorsService;

    /** Returns a DssCommonMessageDetails object if the admin user successfully adds the movie actor or not.
     * @param actorsDto actorsDto
     * @return String
     * @see #addActor(ActorsDTO)
     */
    @PostMapping("/add-actor.do")
    public DssCommonMessageDetails addActor(@RequestBody ActorsDTO actorsDto) throws MovieNotExistingException {
        return actorsService.addActor(actorsDto);
    }

    /** Returns a DssCommonMessageDetails object
     * @return List<Actors>
     * @see #displayActors()
     */
    @GetMapping("/display-actors.do")
    public DssCommonMessageDetails displayActors(){
        return actorsService.displayActors();
    }

    /** Returns a DssCommonMessageDetails object
     * @param firstName firstName
     * @param lastName lastName
     * @return List<Actors>
     * @see #searchActorByActorName(String, String)
     */
    @GetMapping("/search-actor.do/{firstName}/{lastName}")
    public DssCommonMessageDetails searchActorByActorName(@PathVariable("firstName") String firstName,
                                                          @PathVariable("lastName") String lastName){
        return actorsService.searchActorByActorName(firstName, lastName);
    }

    /** Returns a DssCommonMessageDetails object if the admin user successfully updates the movie actor or not.
     * @param actorsDto actorsDto
     * @return String
     * @see #updateActor(ActorsDTO)
     */
    @PutMapping("/update-actor.do")
    public DssCommonMessageDetails updateActor(@RequestBody ActorsDTO actorsDto) throws MovieNotExistingException {
        return actorsService.updateActor(actorsDto);
    }

    /** Returns a DssCommonMessageDetails object if the admin user successfully deletes the movie actor or not.
     * @param firstName firstName
     * @param lastName lastName
     * @return String
     * @see #deleteActor(String, String)
     */
    @DeleteMapping("/delete-actor.do/{firstName}/{lastName}")
    public DssCommonMessageDetails deleteActor(@PathVariable("firstName") String firstName,
                                               @PathVariable("lastName") String lastName){
        return actorsService.deleteActor(firstName, lastName);
    }
}
