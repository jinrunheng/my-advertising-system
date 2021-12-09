package com.github.ad.service.impl;

import com.github.ad.constant.ErrorMsg;
import com.github.ad.dao.AdPlanRepository;
import com.github.ad.dao.AdUnitRepository;
import com.github.ad.dao.CreativeRepository;
import com.github.ad.dao.unit_condition.AdUnitDistrictRepository;
import com.github.ad.dao.unit_condition.AdUnitItRepository;
import com.github.ad.dao.unit_condition.AdUnitKeywordRepository;
import com.github.ad.dao.unit_condition.CreativeUnitRepository;
import com.github.ad.entity.AdPlan;
import com.github.ad.entity.AdUnit;
import com.github.ad.entity.unit_condition.AdUnitDistrict;
import com.github.ad.entity.unit_condition.AdUnitIt;
import com.github.ad.entity.unit_condition.AdUnitKeyword;
import com.github.ad.entity.unit_condition.CreativeUnit;
import com.github.ad.exception.AdException;
import com.github.ad.request.*;
import com.github.ad.response.*;
import com.github.ad.service.AdUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdUnitServiceImpl implements AdUnitService {

    @Autowired
    private AdPlanRepository planRepository;

    @Autowired
    private AdUnitRepository unitRepository;

    @Autowired
    private AdUnitKeywordRepository unitKeywordRepository;

    @Autowired
    private AdUnitItRepository unitItRepository;

    @Autowired
    private AdUnitDistrictRepository unitDistrictRepository;

    @Autowired
    private CreativeRepository creativeRepository;

    @Autowired
    private CreativeUnitRepository creativeUnitRepository;

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

    @Override
    public AdUnitKeywordResponse createUnitKeyword(AdUnitKeywordRequest request) throws AdException {
        List<Long> unitIds = request.getUnitKeywords()
                .stream()
                .map(AdUnitKeywordRequest.UnitKeyword::getUnitId)
                .collect(Collectors.toList());

        if (!isRelatedUnitExist(unitIds)) {
            throw new AdException(ErrorMsg.REQUEST_PARAM_ERROR);
        }

        List<AdUnitKeyword> unitKeywords = new ArrayList<>();
        request.getUnitKeywords().forEach(
                unitKeyword -> unitKeywords.add(new AdUnitKeyword(unitKeyword.getUnitId(), unitKeyword.getKeyword()))
        );

        unitKeywordRepository.saveAll(unitKeywords);

        return AdUnitKeywordResponse.builder()
                .ids(unitIds)
                .build();
    }

    @Override
    public AdUnitItResponse createUnitIt(AdUnitItRequest request) throws AdException {
        List<Long> unitIds = request.getUnitIts()
                .stream()
                .map(AdUnitItRequest.UnitIt::getUnitId)
                .collect(Collectors.toList());

        if (!isRelatedUnitExist(unitIds)) {
            throw new AdException(ErrorMsg.REQUEST_PARAM_ERROR);
        }

        List<AdUnitIt> unitIts = new ArrayList<>();
        request.getUnitIts().forEach(
                unitIt -> unitIts.add(new AdUnitIt(unitIt.getUnitId(), unitIt.getItTag()))
        );

        unitItRepository.saveAll(unitIts);

        return AdUnitItResponse.builder()
                .ids(unitIds)
                .build();
    }

    @Override
    public AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictRequest request) throws AdException {
        List<Long> unitIds = request.getUnitDistricts()
                .stream()
                .map(AdUnitDistrictRequest.UnitDistrict::getUnitId)
                .collect(Collectors.toList());

        if (!isRelatedUnitExist(unitIds)) {
            throw new AdException(ErrorMsg.REQUEST_PARAM_ERROR);
        }

        List<AdUnitDistrict> unitDistricts = new ArrayList<>();
        request.getUnitDistricts().forEach(
                unitDistrict -> unitDistricts.add(
                        new AdUnitDistrict(unitDistrict.getUnitId(), unitDistrict.getProvince(), unitDistrict.getCity()))
        );

        unitDistrictRepository.saveAll(unitDistricts);
        return AdUnitDistrictResponse.builder()
                .ids(unitIds)
                .build();
    }

    @Override
    public CreativeUnitResponse createCreativeUnit(CreativeUnitRequest request) throws AdException {
        List<Long> unitIds = request.getUnitItems()
                .stream()
                .map(CreativeUnitRequest.CreativeUnitItem::getUnitId)
                .collect(Collectors.toList());
        List<Long> creativeIds = request.getUnitItems()
                .stream()
                .map(CreativeUnitRequest.CreativeUnitItem::getCreativeId)
                .collect(Collectors.toList());
        if (!isRelatedUnitExist(unitIds) || !isRelatedCreativeExist(creativeIds)) {
            throw new AdException(ErrorMsg.REQUEST_PARAM_ERROR);
        }

        List<CreativeUnit> creativeUnits = new ArrayList<>();
        request.getUnitItems().forEach(unitItem -> {
            creativeUnits.add(new CreativeUnit(unitItem.getCreativeId(), unitItem.getUnitId()));
        });

        List<Long> ids = creativeUnitRepository.saveAll(creativeUnits)
                .stream()
                .map(CreativeUnit::getId)
                .collect(Collectors.toList());

        return CreativeUnitResponse.builder()
                .ids(ids)
                .build();
    }

    private boolean isRelatedUnitExist(List<Long> unitIds) {
        if (CollectionUtils.isEmpty(unitIds)) {
            return false;
        }
        return unitRepository.findAllById(unitIds).size() == new HashSet<>(unitIds).size();
    }

    private boolean isRelatedCreativeExist(List<Long> creativeIds) {
        if (CollectionUtils.isEmpty(creativeIds)) {
            return false;
        }
        return creativeRepository.findAllById(creativeIds).size() == new HashSet<>(creativeIds).size();
    }
}
