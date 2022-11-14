package com.dss.service.auth;

import com.dss.Resources;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationServiceTest {

    @MockBean
    private UsersRepository userRepository;

    @Autowired
    private LoginAuthenticationService loginAuthenticationService;

    private final Resources resources = new Resources();

    @Test
    public void testLoginSuccess(){
        Mockito.when(userRepository.findUserByEmailAddress("glenmark.ghl@gmail.com")).thenReturn(resources.users());
        DssCommonMessageDetails commonMsgDtl = loginAuthenticationService.login("glenmark.ghl@gmail.com", "P@$$w0rd1234");
        Assert.assertEquals(Boolean.TRUE, commonMsgDtl.isSuccess());
    }

    @Test
    public void testIncorrectEmail(){
        Mockito.when(userRepository.findUserByEmailAddress("glenmark.ghl@gmail.com")).thenReturn(resources.users());
        DssCommonMessageDetails commonMsgDtl = loginAuthenticationService.login("incorrect.email@gmail.com", "P@$$w0rd1234");
        Assert.assertEquals(Boolean.FALSE, commonMsgDtl.isSuccess());
    }

    @Test
    public void testIncorrectPassword(){
        Mockito.when(userRepository.findUserByEmailAddress("glenmark.ghl@gmail.com")).thenReturn(resources.users());
        DssCommonMessageDetails commonMsgDtl = loginAuthenticationService.login("glenmark.ghl@gmail.com", "incorrectpwd");
        Assert.assertEquals(Boolean.FALSE, commonMsgDtl.isSuccess());
    }

}
