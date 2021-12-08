package com.github.ad.service.impl;

import com.github.ad.constant.ErrorMsg;
import com.github.ad.dao.AdUserRepository;
import com.github.ad.entity.AdUser;
import com.github.ad.exception.AdException;
import com.github.ad.request.CreateUserRequest;
import com.github.ad.response.CreateUserResponse;
import com.github.ad.service.AdUserService;
import com.github.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class AdUserServiceImpl implements AdUserService {

    @Autowired
    private AdUserRepository userRepository;

    @Override
    @Transactional
    public CreateUserResponse createUser(CreateUserRequest request) throws AdException {
        if (!request.validate()) {
            throw new AdException(ErrorMsg.REQUEST_PARAM_ERROR);
        }
        if (userRepository.findByUsername(request.getUsername()) != null) {
            throw new AdException(ErrorMsg.SAME_NAME_ERROR);
        }
        AdUser newUser = userRepository.save(new AdUser(request.getUsername(), CommonUtils.md5(request.getUsername())));
        return CreateUserResponse.builder()
                .userId(newUser.getId())
                .username(newUser.getUsername())
                .token(newUser.getToken())
                .creatTime(newUser.getCreateTime())
                .updateTime(newUser.getUpdateTime())
                .build();
    }
}
