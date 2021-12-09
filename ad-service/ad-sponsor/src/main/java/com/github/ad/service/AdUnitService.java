package com.github.ad.service;

import com.github.ad.exception.AdException;
import com.github.ad.request.AdUnitDistrictRequest;
import com.github.ad.request.AdUnitItRequest;
import com.github.ad.request.AdUnitKeywordRequest;
import com.github.ad.request.AdUnitRequest;
import com.github.ad.response.AdUnitDistrictResponse;
import com.github.ad.response.AdUnitItResponse;
import com.github.ad.response.AdUnitKeywordResponse;
import com.github.ad.response.AdUnitResponse;

public interface AdUnitService {
    AdUnitResponse createUnit(AdUnitRequest request) throws AdException;

    AdUnitKeywordResponse createUnitKeyword(AdUnitKeywordRequest request) throws AdException;

    AdUnitItResponse createUnitIt(AdUnitItRequest request) throws AdException;

    AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictRequest request) throws AdException;
}
