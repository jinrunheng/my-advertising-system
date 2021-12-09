package com.github.ad.service;

import com.github.ad.exception.AdException;
import com.github.ad.request.AdUnitRequest;
import com.github.ad.response.AdUnitResponse;

public interface AdUnitService {
    AdUnitResponse createUnit(AdUnitRequest request) throws AdException;
}
