package com.github.ad.controller;

import com.alibaba.fastjson.JSON;
import com.github.ad.exception.AdException;
import com.github.ad.request.CreativeRequest;
import com.github.ad.response.CreativeResponse;
import com.github.ad.service.CreativeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CreativeController {

    @Autowired
    private CreativeService creativeService;

    @PostMapping("/create/creative")
    public CreativeResponse createCreative(@RequestBody CreativeRequest request) throws AdException {
        log.info("ad-sponsor: createCreative -> {}", JSON.toJSONString(request));
        return creativeService.createCreative(request);
    }
}
