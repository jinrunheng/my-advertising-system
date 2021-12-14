package com.github.ad.index.adplan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdPlanObj {

    private Long planId;
    private Long userId;
    private Integer planStatus;
    private Date startDate;
    private Date endDate;

    public void update(AdPlanObj adPlanObj) {
        if (adPlanObj.getPlanId() != null) {
            this.planId = adPlanObj.getPlanId();
        }
        if (adPlanObj.getUserId() != null) {
            this.userId = adPlanObj.getUserId();
        }
        if (adPlanObj.getPlanStatus() != null) {
            this.planStatus = adPlanObj.getPlanStatus();
        }
        if (adPlanObj.getStartDate() != null) {
            this.startDate = adPlanObj.getStartDate();
        }
        if (adPlanObj.getEndDate() != null) {
            this.endDate = adPlanObj.getEndDate();
        }

    }
}
