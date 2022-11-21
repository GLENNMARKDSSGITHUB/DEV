package com.dss.client.service.auth;

import com.dss.client.proxy.auth.AuthenticationProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    @Autowired
    AuthenticationProxy authenticationStoreClient;

    @Override
    public Map<String, String> login(Map<String, String> obj) {
        return authenticationStoreClient.login(obj);
    }

}
