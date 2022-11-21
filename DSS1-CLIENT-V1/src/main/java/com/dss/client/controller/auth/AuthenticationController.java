package com.dss.client.controller.auth;

import com.dss.client.service.auth.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/API")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login.do")
    public Map<String, String> login(@RequestBody Map<String, String> obj){
        return authenticationService.login(obj);
    }
}
