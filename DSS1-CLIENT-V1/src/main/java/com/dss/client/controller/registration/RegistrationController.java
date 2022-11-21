package com.dss.client.controller.registration;

import com.dss.client.service.registration.RegistrationService;
import com.dss.client.utils.DssCommonMessageDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/API/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/add-registration.do")
    DssCommonMessageDetails addRegistration(@RequestBody Object obj){
        return registrationService.addRegistration(obj);
    }

    @GetMapping("/display-registrations.do")
    public DssCommonMessageDetails displayAccountRegistrations(){
        return registrationService.displayAccountRegistrations();
    }
}
