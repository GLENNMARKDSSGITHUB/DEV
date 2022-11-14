package com.dss.client.service.registration;

import com.dss.client.proxy.registration.RegistrationProxy;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RegistrationServiceImpl implements RegistrationService{

    @Autowired
    RegistrationProxy registrationProxy;

    @Override
    public String addRegistration(Object obj) {
        return registrationProxy.addRegistration(obj);
    }

}
