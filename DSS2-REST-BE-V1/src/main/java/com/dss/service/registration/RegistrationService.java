/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.service.registration;

import com.dss.dto.user.UsersDTO;
import com.dss.util.utils.DssCommonMessageDetails;

/**
 * This is a service layer for DSS Registration
 */

public interface RegistrationService {

    /** Returns a DssCommonMessageDetails object if the admin user successfully registers the account or not.
     * @param user userDto
     * @return DssCommonMessageDetails commonMsgDtl
     * @see #registerAccount(UsersDTO)
     */
    DssCommonMessageDetails registerAccount(UsersDTO user);

    /** Returns a DssCommonMessageDetails object
     * @return DssCommonMessageDetails commonMsgDtl
     * @see #displayRegistrations()
     */
    DssCommonMessageDetails displayRegistrations();
}
