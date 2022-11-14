package com.dss.client.controller.registration;

import com.dss.client.service.registration.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/API/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/add-registration.do")
    String addRegistration(@RequestBody Object obj){
        return registrationService.addRegistration(obj);
    }
}
