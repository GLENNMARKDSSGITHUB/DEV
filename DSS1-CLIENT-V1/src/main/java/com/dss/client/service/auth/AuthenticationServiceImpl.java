package com.dss.client.service.auth;

import com.dss.client.proxy.auth.AuthenticationProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    @Autowired
    AuthenticationProxy authenticationStoreClient;

    @Override
    public String login(Object obj) {
        return authenticationStoreClient.login(obj);
    }

}
