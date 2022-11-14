package com.dss;

import com.dss.dto.UsersDTO;
import com.dss.entity.roles.Roles;
import com.dss.entity.user.Users;
import com.dss.util.enums.UserRoles;
import com.dss.util.enums.UserStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Resources {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users user(){
        return new Users(
                "US0001",
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
    }

    public Users users(){
        return new Users(
                "US0001",
                "Glenn Mark",
                "Anduiza",
                "glenmark.ghl@gmail.com",
                encoder.encode("P@$$w0rd1234"),
                UserStatus.ACTIVE.toString(),
                "09106121529",
                new Date(),
                UserRoles.ROLE_SUPER_ADMIN.getStrRole(),
                null,
                null,
                Collections.singletonList(new Roles(
                        "RS1",
                        UserRoles.ROLE_SUPER_ADMIN.getStrRole()
                ))
        );
    }

    public Users userForValidation(){
        return new Users(
                "US0001",
                "Glenn Mark@3",
                "Anduiza@3",
                "@gmail.com",
                encoder.encode("P@$$w0rd1234"),
                UserStatus.ACTIVE.toString(),
                "09106121529",
                new Date(),
                UserRoles.ROLE_SUPER_ADMIN.getStrRole(),
                null,
                null,
                Collections.singletonList(new Roles(
                        "RS1",
                        UserRoles.ROLE_SUPER_ADMIN.getStrRole()
                ))
        );
    }

    @Valid
    public UsersDTO userDto(){
        return new UsersDTO(
                "US0001",
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
    }

    public Roles role(){
        return new Roles(
                "RS1",
                UserRoles.ROLE_SUPER_ADMIN.getStrRole(),
                user()
        );
    }
}
