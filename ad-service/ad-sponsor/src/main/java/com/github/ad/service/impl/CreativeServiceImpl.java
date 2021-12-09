package com.github.ad.service.impl;

import com.github.ad.dao.CreativeRepository;
import com.github.ad.entity.Creative;
import com.github.ad.request.CreativeRequest;
import com.github.ad.response.CreativeResponse;
import com.github.ad.service.CreativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreativeServiceImpl implements CreativeService {

    @Autowired
    private CreativeRepository creativeRepository;

    @Override
    public CreativeResponse createCreative(CreativeRequest request) {
        Creative save = creativeRepository.save(request.convertToEntity());
        return CreativeResponse.builder()
                .id(save.getId())
                .name(save.getName())
                .build();
    }
}
