package com.dss.client.service.auth;

import com.dss.client.utils.DssCommonMessageDetails;

public interface AuthenticationService {

    DssCommonMessageDetails login(Object obj);
}
