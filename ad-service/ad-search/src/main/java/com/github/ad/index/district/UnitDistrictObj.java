package com.github.ad.index.district;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnitDistrictObj {
    private Long unitId;
    private String province;
    private String city;
}
