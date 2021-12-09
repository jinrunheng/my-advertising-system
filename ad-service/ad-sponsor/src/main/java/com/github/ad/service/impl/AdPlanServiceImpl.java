package com.github.ad.service.impl;

import com.github.ad.constant.CommonStatus;
import com.github.ad.constant.ErrorMsg;
import com.github.ad.dao.AdPlanRepository;
import com.github.ad.dao.AdUserRepository;
import com.github.ad.entity.AdPlan;
import com.github.ad.entity.AdUser;
import com.github.ad.exception.AdException;
import com.github.ad.request.AdPlanGetRequest;
import com.github.ad.request.AdPlanRequest;
import com.github.ad.response.AdPlanResponse;
import com.github.ad.service.AdPlanService;
import com.github.ad.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AdPlanServiceImpl implements AdPlanService {

    @Autowired
    private AdUserRepository userRepository;

    @Autowired
    private AdPlanRepository planRepository;

    @Override
    @Transactional
    public AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException {
        if (!request.createValidate()) {
            throw new AdException(ErrorMsg.REQUEST_PARAM_ERROR);
        }

        // 确保关联的 User 是存在的
        Optional<AdUser> user = userRepository.findById(request.getUserId());
        if (!user.isPresent()) {
            throw new AdException(ErrorMsg.CAN_NOT_FIND_RECORD);
        }
        if (planRepository.findByUserIdAndPlanName(request.getUserId(), request.getPlanName()) != null) {
            throw new AdException(ErrorMsg.SAME_NAME_PLAN_ERROR);
        }
        AdPlan newPlan = AdPlan.builder()
                .userId(request.getUserId())
                .planName(request.getPlanName())
                .startDate(CommonUtils.parseStringDate(request.getStartDate()))
                .endDate(CommonUtils.parseStringDate(request.getEndDate()))
                .build();

        AdPlan save = planRepository.save(newPlan);
        return new AdPlanResponse(save.getId(), save.getPlanName());
    }

    @Override
    public List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) throws AdException {
        if (!request.validate()) {
            throw new AdException(ErrorMsg.REQUEST_PARAM_ERROR);
        }
        return planRepository.findAllByIdInAndUserId(request.getIds(), request.getUserId());
    }

    @Override
    @Transactional
    public AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException {
        if (!request.updateValidate()) {
            throw new AdException(ErrorMsg.REQUEST_PARAM_ERROR);
        }
        AdPlan plan = planRepository.findByIdAndUserId(request.getId(), request.getUserId());
        if (plan == null) {
            throw new AdException(ErrorMsg.CAN_NOT_FIND_RECORD);
        }
        if (request.getPlanName() != null) {
            plan.setPlanName(request.getPlanName());
        }
        if (request.getStartDate() != null) {
            plan.setStartDate(CommonUtils.parseStringDate(request.getStartDate()));
        }
        if (request.getEndDate() != null) {
            plan.setEndDate(CommonUtils.parseStringDate(request.getEndDate()));
        }

        plan.setUpdateTime(new Date());
        AdPlan save = planRepository.save(plan);
        return AdPlanResponse.builder()
                .id(save.getId())
                .planName(save.getPlanName())
                .build();
    }

    @Override
    @Transactional
    public void deleteAdPlan(AdPlanRequest request) throws AdException {
        if (!request.deleteValidate()) {
            throw new AdException(ErrorMsg.REQUEST_PARAM_ERROR);
        }
        AdPlan plan = planRepository.findByIdAndUserId(request.getId(), request.getUserId());
        if (plan == null) {
            throw new AdException(ErrorMsg.CAN_NOT_FIND_RECORD);
        }

        plan.setPlanStatus(CommonStatus.INVALID.getStatus());
        plan.setUpdateTime(new Date());
        planRepository.save(plan);
    }
}
