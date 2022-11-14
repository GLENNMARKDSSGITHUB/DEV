/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.transformer;

import com.dss.dto.UsersDTO;
import com.dss.entity.roles.Roles;
import com.dss.entity.user.Users;
import com.dss.util.enums.UserRoles;
import com.dss.util.enums.UserStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is a DSS2 Account Registration Transformer
 */

public class RegistrationTransformer {
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Roles transformToRoles(Users user){
        return new Roles(
                UserRoles.ROLE_ADMIN.getStrRoleId(),
                UserRoles.ROLE_ADMIN.getStrRole(),
                user
        );
    }

    public Users transformToUsers(UsersDTO userDto, String ddsUserId){
        return new Users(
                ddsUserId,
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail(),
                encoder.encode(userDto.getPassword()),
                UserStatus.ACTIVE.toString(),
                userDto.getCellphoneNumber(),
                new Date(),
                UserRoles.ROLE_ADMIN.getStrRole(),
                userDto.getLastModificationDate(),
                userDto.getLastModifiedBy()
        );
    }

    public List<Users> transformToUsersList(List<Users> userList){
        List<Users> users = new ArrayList<>();

        for (Users user : userList) {
            List<Roles> rolesList = new ArrayList<>();
            for(Roles r : user.getUserRoles()){
                rolesList.add(new Roles(
                        r.getDssRoleId(),
                        r.getUserRole()
                ));
            }

            users.add(new Users(
                    user.getDssUserId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getStatus(),
                    user.getCellphoneNumber(),
                    user.getCreationDate(),
                    user.getCreatedBy(),
                    user.getLastModificationDate(),
                    user.getLastModifiedBy(),
                    rolesList
            ));
        }
        return users;
    }
}
