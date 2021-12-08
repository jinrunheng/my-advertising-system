package com.github.ad.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * 创意，对应到创意表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "ad_creative")
public class Creative {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 创意类型:
     * 图片，视频...
     */
    @Column(name = "type", nullable = false)
    private Integer type;

    /**
     * 创意的物料类型，属于创意类型的子类型:
     * 图片：
     * - jpg
     * - png
     * 视频：
     * - .avi
     * - .mp4
     */
    @Column(name = "material_type", nullable = false)
    private Integer materialType;

    /**
     * 物料的高度
     */
    @Column(name = "height", nullable = false)
    private Integer height;

    /**
     * 物料的宽度
     */
    @Column(name = "width", nullable = false)
    private Integer width;

    /**
     * 物料的大小
     */
    @Column(name = "size", nullable = false)
    private Long size;

    /**
     * 物料持续的时长，只有当物料为视频时，才不会为 0
     */
    @Column(name = "duration", nullable = false)
    private Integer duration;

    /**
     * 审核状态
     */
    @Column(name = "audit_status", nullable = false)
    private Integer auditStatus;

    /**
     * 物料所属用户
     */
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /**
     * 物料所在的地址
     */
    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "create_time", nullable = false)
    private Date createTime;

    @Column(name = "update_time", nullable = false)
    private Date updateTime;


}
