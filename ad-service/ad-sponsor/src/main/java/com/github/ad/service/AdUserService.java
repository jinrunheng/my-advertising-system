package com.github.ad.service;

import com.github.ad.exception.AdException;
import com.github.ad.request.CreateUserRequest;
import com.github.ad.response.CreateUserResponse;

public interface AdUserService {
    /**
     * 创建用户
     *
     * @param request
     * @return
     * @throws AdException
     */
    CreateUserResponse createUser(CreateUserRequest request) throws AdException;
}
