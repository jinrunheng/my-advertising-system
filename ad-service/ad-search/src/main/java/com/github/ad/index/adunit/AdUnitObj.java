package com.github.ad.index.adunit;

import com.github.ad.index.adplan.AdPlanObj;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdUnitObj {
    private Long unitId;
    private Integer unitStatus;
    private Integer positionType;
    private Long planId;

    private AdPlanObj adPlanObj;

    public void update(AdUnitObj adUnitObj) {
        if (adUnitObj.getUnitId() != null) {
            this.unitId = adUnitObj.getUnitId();
        }
        if (adUnitObj.getUnitStatus() != null) {
            this.unitStatus = adUnitObj.getUnitStatus();
        }
        if (adUnitObj.getPositionType() != null) {
            this.positionType = adUnitObj.getPositionType();
        }
        if (adUnitObj.getPlanId() != null) {
            this.planId = adUnitObj.getPlanId();
        }
        if (adUnitObj.getAdPlanObj() != null) {
            this.adPlanObj = this.getAdPlanObj();
        }
    }

}
