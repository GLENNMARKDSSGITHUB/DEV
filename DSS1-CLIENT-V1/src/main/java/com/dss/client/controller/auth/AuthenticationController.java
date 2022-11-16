package com.dss.client.controller.auth;

import com.dss.client.service.auth.AuthenticationService;
import com.dss.client.utils.DssCommonMessageDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/API")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login.do")
    public DssCommonMessageDetails login(@RequestBody Object obj){
        return authenticationService.login(obj);
    }
}
