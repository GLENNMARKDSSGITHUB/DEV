/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.controller.auth;

import com.dss.dto.UsersDTO;
import com.dss.service.auth.LoginAuthenticationService;
import com.dss.util.utils.DssCommonMessageDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is a controller layer for DSS Login and Authentication
 */

@RestController
@RequestMapping("/API")
public class LoginAuthenticationController {

    @Autowired
    private LoginAuthenticationService loginAuthenticationService;

    /** Returns a DssCommonMessageDetails object if the user successfully login to the DSS web app or not
     * @param userDto userDto
     * @return String
     * @see #login(UsersDTO)
     */
    @PostMapping("/login.do")
    public DssCommonMessageDetails login(@RequestBody UsersDTO userDto){
        return loginAuthenticationService.login(userDto.getEmail(), userDto.getPassword());
    }
}
