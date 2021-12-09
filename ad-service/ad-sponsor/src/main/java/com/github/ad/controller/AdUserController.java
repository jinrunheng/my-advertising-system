package com.github.ad.controller;

import com.alibaba.fastjson.JSON;
import com.github.ad.exception.AdException;
import com.github.ad.request.CreateUserRequest;
import com.github.ad.response.CreateUserResponse;
import com.github.ad.service.AdUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AdUserController {

    @Autowired
    private AdUserService userService;

    @PostMapping("/create/user")
    public CreateUserResponse createUser(@RequestBody CreateUserRequest request) throws AdException {
        log.info("ad-sponsor: createUser -> {}", JSON.toJSONString(request));
        return userService.createUser(request);
    }


}
