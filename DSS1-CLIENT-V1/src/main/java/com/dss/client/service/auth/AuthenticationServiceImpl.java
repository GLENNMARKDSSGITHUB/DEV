package com.dss.client.service.auth;

import com.dss.client.proxy.auth.AuthenticationProxy;
import com.dss.client.utils.DssCommonMessageDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    @Autowired
    AuthenticationProxy authenticationStoreClient;

    @Override
    public DssCommonMessageDetails login(Object obj) {
        return authenticationStoreClient.login(obj);
    }

}
