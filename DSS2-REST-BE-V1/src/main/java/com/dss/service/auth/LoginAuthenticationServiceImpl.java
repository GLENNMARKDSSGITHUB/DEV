/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.service.auth;

import com.dss.entity.user.Users;
import com.dss.repository.user.UsersRepository;
import com.dss.util.utils.CommonStringUtility;
import com.dss.util.utils.DssCommonMessageDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * This class is a service implementation for DSS Login and Authentication
 * @see #login(String , String)
 */

@Service
public class LoginAuthenticationServiceImpl implements LoginAuthenticationService {
    private final DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    private UsersRepository userRepository;

    @Override
    public DssCommonMessageDetails login(String email, String password) {
        Users user = userRepository.findUserByEmailAddress(email);
        if(!Objects.isNull(user)){
            if(encoder.matches(password, user.getPassword())){
                commonMsgDtl.setSuccess(true);
                commonMsgDtl.setContent("Welcome to Digistream Express!");
            }else{
                commonMsgDtl.setSuccess(false);
                commonMsgDtl.setContent(CommonStringUtility.ERR_CODE_LOGIN_INCORRECT_PASSWORD);
            }
        }else{
            commonMsgDtl.setSuccess(false);
            commonMsgDtl.setContent(CommonStringUtility.ERR_CODE_LOGIN_EMAIL_NOT_CONNECTED);
        }
        return commonMsgDtl;
    }
}
