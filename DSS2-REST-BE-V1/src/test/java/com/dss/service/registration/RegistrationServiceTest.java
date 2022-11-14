package com.dss.service.registration;

import com.dss.Resources;
import com.dss.entity.user.Users;
import com.dss.repository.roles.RolesRepository;
import com.dss.repository.user.UsersRepository;
import com.dss.util.utils.DssCommonMessageDetails;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegistrationServiceTest {

    @MockBean
    private UsersRepository userRepository;

    @MockBean
    private RolesRepository rolesRepository;

    @Autowired
    private RegistrationService registrationService;

    private final Resources resources = new Resources();

    @Test
    public void testRegisterAccountSuccess(){
        userRepository.save(resources.user());
        rolesRepository.save(resources.role());
        Mockito.when(userRepository.save(resources.user())).thenReturn(resources.user());
        DssCommonMessageDetails commonMsgDtl = registrationService.registerAccount(resources.userDto());
        Assert.assertEquals(Boolean.TRUE, commonMsgDtl.isSuccess());
    }

    @Test
    public void testDisplayRegistrationsSuccess(){
        Mockito.when(userRepository.findAll()).thenReturn(Collections.singletonList(resources.users()));
        DssCommonMessageDetails commonMsgDtl = registrationService.displayRegistrations();
        Assert.assertEquals(1, commonMsgDtl.getObjList().size());
    }

    @Test
    public void testDisplayRegistrationsFailed(){
        List<Users> usersList = new ArrayList<>();
        Mockito.when(userRepository.findAll()).thenReturn(usersList);
        DssCommonMessageDetails  commonMsgDtl = registrationService.displayRegistrations();
        Assert.assertEquals(Boolean.FALSE, commonMsgDtl.isSuccess());
    }
}
