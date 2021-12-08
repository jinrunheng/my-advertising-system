package com.github.ad.constant;

import lombok.Getter;

/**
 * 创意类型的子类型：物料类型
 */
@Getter
public enum CreativeMaterialType {
    JPG(1, "jpg"),
    PNG(2, "png"),

    MP4(3, "mp4"),
    AVI(4, "avi"),

    TXT(5, "txt");

    private int type;
    private String desc;

    CreativeMaterialType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
