/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.service.registration;

import com.dss.dto.UsersDTO;
import com.dss.entity.roles.Roles;
import com.dss.entity.user.Users;
import com.dss.repository.roles.RolesRepository;
import com.dss.repository.user.UsersRepository;
import com.dss.transformer.RegistrationTransformer;
import com.dss.util.utils.CommonStringUtility;
import com.dss.util.utils.DssCommonMessageDetails;
import com.fasterxml.uuid.Generators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * This class is a service implementation for DSS Account Registration
 * @see #registerAccount(UsersDTO)
 * @see #displayRegistrations()
 */

@Service
public class RegistrationServiceImpl implements RegistrationService{

    private final RegistrationTransformer tf = new RegistrationTransformer();

    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public DssCommonMessageDetails registerAccount(UsersDTO userDto){
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        Users user = tf.transformToUsers(userDto, this.generateDssUserId());
        userRepository.save(user);
        Roles role = tf.transformToRoles(user);
        rolesRepository.save(role);
        commonMsgDtl.setSuccess(true);
        commonMsgDtl.setContent(CommonStringUtility.SUCCESS_MSG_CREATE_ACCT);
        return commonMsgDtl;
    }

    @Override
    public DssCommonMessageDetails displayRegistrations() {
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        List<Users> userList = userRepository.findAll();
        if(!userList.isEmpty()){
            commonMsgDtl.setObjList(tf.transformToUsersList(userList));
            commonMsgDtl.setSuccess(true);
        }else{
            commonMsgDtl.setContent(CommonStringUtility.ERR_MSG_NO_DISPLAY_RECORDS);
            commonMsgDtl.setSuccess(false);
        }
        return commonMsgDtl;
    }

    private String generateDssUserId(){
        UUID uuid = Generators.timeBasedGenerator().generate();
        return String.format(CommonStringUtility.DSS_USER_ID, uuid.toString().substring(0, 8).toUpperCase());
    }
}
