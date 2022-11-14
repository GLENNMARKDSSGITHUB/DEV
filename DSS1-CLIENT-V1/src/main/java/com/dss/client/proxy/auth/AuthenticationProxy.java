package com.dss.client.proxy.auth;

import com.dss.client.configuration.CustomFeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "${feign.login-register-name}" , url ="${feign.login-register-url}", configuration = CustomFeignClientConfiguration.class)
public interface AuthenticationProxy {

    @PostMapping("/API/login.do")
    String login(Object obj);
}
