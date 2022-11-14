package com.dss.controller.registration;

import com.dss.Resources;
import com.dss.repository.user.UsersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegistrationServiceTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private UsersRepository userRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    private final Resources resources = new Resources();

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testRegisterAccountSuccess() throws Exception {
        String jsonRequest = objectMapper.writeValueAsString(resources.users());
        mockMvc.perform(MockMvcRequestBuilders.post("/API/registration/add-registration.do")
                .content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testShouldbeFailedToRegisterAccount() throws Exception {
        String jsonRequest = objectMapper.writeValueAsString(resources.userForValidation());
        mockMvc.perform(MockMvcRequestBuilders.post("/API/registration/add-registration.do")
                .content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testDisplayRegistrations() throws Exception {
        Mockito.when(userRepository.findAll()).thenReturn(Collections.singletonList(resources.users()));

        String jsonRequest = objectMapper.writeValueAsString(resources.users());
        mockMvc.perform(MockMvcRequestBuilders.get("/API/registration/display-registrations.do")
                .content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
