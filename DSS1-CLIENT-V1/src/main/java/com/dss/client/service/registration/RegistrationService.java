package com.dss.client.service.registration;

import com.dss.client.utils.DssCommonMessageDetails;

public interface RegistrationService {

    DssCommonMessageDetails addRegistration(Object obj);

   DssCommonMessageDetails displayAccountRegistrations();
}
