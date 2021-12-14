package com.github.ad.index.creative;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreativeObj {
    // 创意 id
    private Long adId;
    private String name;
    private Integer type;
    private Integer materialType;
    private Integer height;
    private Integer width;
    private Integer auditStatus;
    private String adUrl;

    public void update(CreativeObj creativeObj) {
        if (creativeObj.getAdId() != null) {
            this.adId = creativeObj.getAdId();
        }
        if (creativeObj.getName() != null) {
            this.name = creativeObj.getName();
        }
        if (creativeObj.getType() != null) {
            this.type = creativeObj.getType();
        }
        if (creativeObj.getMaterialType() != null) {
            this.type = creativeObj.getMaterialType();
        }
        if (creativeObj.getHeight() != null) {
            this.height = creativeObj.getHeight();
        }
        if (creativeObj.getWidth() != null) {
            this.width = creativeObj.getWidth();
        }
        if (creativeObj.getAuditStatus() != null) {
            this.auditStatus = creativeObj.getAuditStatus();
        }
        if (creativeObj.getAdUrl() != null) {
            this.adUrl = creativeObj.getAdUrl();
        }
    }
}
