package com.github.ad.service.impl;

import com.github.ad.constant.ErrorMsg;
import com.github.ad.dao.AdPlanRepository;
import com.github.ad.dao.AdUnitRepository;
import com.github.ad.entity.AdPlan;
import com.github.ad.entity.AdUnit;
import com.github.ad.exception.AdException;
import com.github.ad.request.AdUnitRequest;
import com.github.ad.response.AdUnitResponse;
import com.github.ad.service.AdUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdUnitServiceImpl implements AdUnitService {

    @Autowired
    private AdPlanRepository planRepository;

    @Autowired
    private AdUnitRepository unitRepository;

    @Override
    public AdUnitResponse createUnit(AdUnitRequest request) throws AdException {
        if (!request.createValidate()) {
            throw new AdException(ErrorMsg.REQUEST_PARAM_ERROR);
        }
        Optional<AdPlan> plan = planRepository.findById(request.getPlanId());
        if (!plan.isPresent()) {
            throw new AdException(ErrorMsg.CAN_NOT_FIND_RECORD);
        }
        if (unitRepository.findByPlanIdAndUnitName(request.getPlanId(), request.getUnitName()) != null) {
            throw new AdException(ErrorMsg.SAME_NAME_UNIT_ERROR);
        }

        AdUnit newAdUnit = AdUnit.builder()
                .planId(request.getPlanId())
                .unitName(request.getUnitName())
                .positionType(request.getPositionType())
                .budget(request.getBudget())
                .build();

        AdUnit save = unitRepository.save(newAdUnit);

        return AdUnitResponse.builder()
                .id(save.getId())
                .unitName(save.getUnitName())
                .build();
    }
}
