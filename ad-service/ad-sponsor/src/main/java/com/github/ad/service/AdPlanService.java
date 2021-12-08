package com.github.ad.service;

import com.github.ad.entity.AdPlan;
import com.github.ad.exception.AdException;
import com.github.ad.request.AdPlanGetRequest;
import com.github.ad.request.AdPlanRequest;
import com.github.ad.response.AdPlanResponse;

import java.util.List;

public interface AdPlanService {
    /**
     * 创建推广计划
     *
     * @param request
     * @return
     * @throws AdException
     */
    AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException;

    /**
     * 获取推广计划
     *
     * @param request
     * @return
     * @throws AdException
     */
    List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) throws AdException;

    /**
     * 更新推广计划
     *
     * @param response
     * @return
     * @throws AdException
     */
    AdPlanResponse updateAdPlan(AdPlanResponse response) throws AdException;

    /**
     * 删除推广计划
     *
     * @param request
     * @throws AdException
     */
    void deleteAdPlan(AdPlanRequest request) throws AdException;
}
