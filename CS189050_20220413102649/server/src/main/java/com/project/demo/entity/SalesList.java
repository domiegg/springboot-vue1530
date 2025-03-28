package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *销售榜单：(SalesList)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "SalesList")
public class SalesList implements Serializable {

    //SalesList编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sales_list_id")
    private Integer sales_list_id;
    // 图书编号
    @Basic
    private String book_number;
    // 图书名称
    @Basic
    private String book_name;
    // 销售数量
    @Basic
    private String sales_volumes;
    // 排名
    @Basic
    private String ranking;
    // 备注
    @Basic
    private String remarks;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
