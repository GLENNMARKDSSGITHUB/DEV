/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.seeder;

import com.dss.entity.roles.Roles;
import com.dss.entity.user.Users;
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

            List<Roles> roleList = Arrays.asList(
                    new Roles("RS1", UserRoles.ROLE_SUPER_ADMIN.getStrRole(), user));
            rolesRepository.saveAll(roleList);
        }
    }

}
