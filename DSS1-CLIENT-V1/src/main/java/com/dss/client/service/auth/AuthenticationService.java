package com.dss.client.service.auth;

import java.util.Map;

public interface AuthenticationService {

    Map<String, String> login(Map<String, String> obj);
}
