/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.service.auth;

import com.dss.util.utils.DssCommonMessageDetails;

/**
 * This is a service interface for Login and Authentication
 */

public interface LoginAuthenticationService {

    /**
     * Returns a String value that indicates if the user login successfully or not.
     * @param email user's email
     * @param password is an ConstraintValidatorContext interface
     * @return DssCommonMessageDetails commonMsgDtl
     * @see #login(String , String)
     */

    DssCommonMessageDetails login(String email, String password);
}
