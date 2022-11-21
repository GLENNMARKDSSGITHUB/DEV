package com.dss.service.token;

import com.dss.util.utils.DssCommonMessageDetails;

public interface TokenService {

    DssCommonMessageDetails generateToken(String email, String password);
}
