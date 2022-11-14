package com.dss.service.security;

import com.dss.Resources;
import com.dss.repository.user.UsersRepository;
import com.dss.security.DssUserDetailsImpl;
import com.dss.security.DssUserDetailsServiceImpl;
import com.dss.util.enums.UserRoles;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringSecurityTest {

    @MockBean
    private UsersRepository usersRepository;

    @Autowired
    private DssUserDetailsServiceImpl dssUserDetailsService;

    private final Resources resources = new Resources();

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    private final String email = "glenmark.ghl@gmail.com";
    private final String role = UserRoles.ROLE_SUPER_ADMIN.getStrRole();

    @Test
    public void testLoadUserByUsername_CheckEmail(){
        Mockito.when(usersRepository.findUserByEmailAddress(email)).thenReturn(resources.users());
        Assert.assertEquals(new DssUserDetailsImpl(resources.users()).getUsername(),
                dssUserDetailsService.loadUserByUsername(email).getUsername());
    }

    @Test
    public void testLoadUserByUsername_CheckPassword(){
        Mockito.when(usersRepository.findUserByEmailAddress(email)).thenReturn(resources.users());
        String password = "P@$$w0rd1234";
        Assert.assertEquals(Boolean.TRUE,
                encoder.matches(password, new DssUserDetailsImpl(resources.users()).getPassword()));
    }

    @Test
    public void testLoadUserByUsername_Authority(){
        Mockito.when(usersRepository.findUserByEmailAddress(email)).thenReturn(resources.users());
        Assert.assertEquals(Collections.singleton(new SimpleGrantedAuthority(role)),
                new DssUserDetailsImpl(resources.users()).getAuthorities());
    }

    @Test
    public void testLoadUserByUsername_isAccountNonExpired(){
        Mockito.when(usersRepository.findUserByEmailAddress(email)).thenReturn(resources.users());
        Assert.assertEquals(Boolean.TRUE, new DssUserDetailsImpl(resources.users()).isAccountNonExpired());
    }

    @Test
    public void testLoadUserByUsername_isAccountNonLocked(){
        Mockito.when(usersRepository.findUserByEmailAddress(email)).thenReturn(resources.users());
        Assert.assertEquals(Boolean.TRUE, new DssUserDetailsImpl(resources.users()).isAccountNonLocked());
    }

    @Test
    public void testLoadUserByUsername_isCredentialsNonExpired(){
        Mockito.when(usersRepository.findUserByEmailAddress(email)).thenReturn(resources.users());
        Assert.assertEquals(Boolean.TRUE, new DssUserDetailsImpl(resources.users()).isCredentialsNonExpired());
    }

    @Test
    public void testLoadUserByUsername_isEnabled(){
        Mockito.when(usersRepository.findUserByEmailAddress(email)).thenReturn(resources.users());
        Assert.assertEquals(Boolean.TRUE, new DssUserDetailsImpl(resources.users()).isEnabled());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testUsernameNotFoundException() throws UsernameNotFoundException{
        Mockito.when(dssUserDetailsService.loadUserByUsername(email)).thenReturn(new DssUserDetailsImpl(resources.users()));
        Mockito.when(dssUserDetailsService.loadUserByUsername(email))
                .thenThrow(new UsernameNotFoundException("Account not found!"));
    }
}
