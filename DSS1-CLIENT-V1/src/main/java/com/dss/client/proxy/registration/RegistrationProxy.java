package com.dss.client.proxy.registration;

import com.dss.client.configuration.CustomFeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(name = "${feign.login-register-name}" , url ="${feign.login-register-url}", configuration = CustomFeignClientConfiguration.class)
public interface RegistrationProxy {

    @PostMapping("/API/registration/add-registration.do")
    String addRegistration(Object obj);

}
