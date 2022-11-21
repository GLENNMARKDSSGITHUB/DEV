/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.seeder;

import com.dss.entity.action.Action;
import com.dss.entity.permission.Permission;
import com.dss.entity.resources.Resources;
import com.dss.entity.roles.Roles;
import com.dss.entity.user.Users;
import com.dss.repository.action.ActionRepository;
import com.dss.repository.permission.PermissionRepository;
import com.dss.repository.resources.ResourcesRepository;
import com.dss.repository.roles.RolesRepository;
import com.dss.repository.user.UsersRepository;
import com.dss.util.enums.UserRoles;
import com.dss.util.enums.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * This class is a Database Seeder for DSS web app
 */

@Component
public class DatabaseSeeder {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private ResourcesRepository resourcesRepository;

    @Autowired
    private ActionRepository actionRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedUser();
    }

    private void seedUser(){
        List<Users> users = usersRepository.findAll();
        if(users.isEmpty()){
            Users user = new Users(
                    "USCF718F3C",
                    "Glenn Mark",
                    "Anduiza",
                    "glenmark.ghl@gmail.com",
                    encoder.encode("P@$$w0rd1234"),
                    UserStatus.ACTIVE.toString(),
                    "09106121529",
                    new Date(),
                    UserRoles.ROLE_SUPER_ADMIN.getStrRole(),
                    null,
                    null
            );
            usersRepository.save(user);

            Roles roleSuperAdmin =  new Roles("RS1", UserRoles.ROLE_SUPER_ADMIN.getStrRole(), user);
            Roles roleAdmin = new Roles("RS2", UserRoles.ROLE_ADMIN.getStrRole(), user);

            List<Roles> roleList = Arrays.asList(roleSuperAdmin, roleAdmin);
            rolesRepository.saveAll(roleList);

            Permission pSuperAdmin = new Permission("RS1_P1", "P_SUPER_ADMIN", roleSuperAdmin);
            List<Permission> permissionList = Collections.singletonList(pSuperAdmin);
            permissionRepository.saveAll(permissionList);

            Resources rAuth = new Resources("P1_R1", "R_AUTH", pSuperAdmin);
            Resources rAccount = new Resources("P1_R2", "R_REGISTRATION", pSuperAdmin);
            Resources rMovie = new Resources("P1_R3", "R_MOVIE", pSuperAdmin);
            Resources rActor = new Resources("P1_R4", "R_ACTOR", pSuperAdmin);
            Resources rReviews = new Resources("P1_R5", "R_REVIEWS", pSuperAdmin);
            List<Resources> resourcesList = Arrays.asList(rAuth, rAccount, rMovie, rActor, rReviews);
            resourcesRepository.saveAll(resourcesList);

            Action aAuth = new Action("R1_A1", "A_AUTH", rAccount);

            Action aCreateAcct = new Action("R2_A1", "A_CREATE_ACCOUNT", rAccount);
            Action aViewAcct = new Action("R2_A2", "A_VIEW_ACCOUNTS", rAccount);

            Action aAddMovie = new Action("R3_A1", "A_ADD_MOVIE", rMovie);
            Action aViewMovies = new Action("R3_A2", "A_VIEW_MOVIES", rMovie);
            Action aSearchMovie = new Action("R3_A3", "A_SEARCH_MOVIE", rMovie);
            Action aUpdateMovie = new Action("R3_A4", "A_UPDATE_MOVIE", rMovie);
            Action aDeleteMovie = new Action("R3_A5", "A_DELETE_MOVIE", rMovie);

            Action aAddActor = new Action("R4_A1", "A_ADD_ACTOR", rActor);
            Action aViewActors = new Action("R4_A2", "A_VIEW_ACTORS", rActor);
            Action aSearchActor = new Action("R4_A3", "A_SEARCH_ACTOR", rActor);
            Action aUpdateActor = new Action("R4_A4", "A_UPDATE_ACTOR", rActor);
            Action aDeleteActor = new Action("R4_A5", "A_DELETE_ACTOR", rActor);

            Action aAddReview = new Action("R5_A1", "A_ADD_REVIEW", rReviews);
            Action aViewReviews = new Action("R5_A2", "A_VIEW_REVIEWS", rReviews);

            List<Action> actionList = Arrays.asList(
                    aAuth,
                    aCreateAcct, aViewAcct,
                    aAddMovie, aViewMovies, aSearchMovie, aUpdateMovie, aDeleteMovie,
                    aAddActor, aViewActors, aSearchActor, aUpdateActor, aDeleteActor,
                    aAddReview, aViewReviews
            );
            actionRepository.saveAll(actionList);
        }
    }

}
