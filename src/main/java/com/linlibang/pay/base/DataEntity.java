/*
 * Copyright &copy; 2017 <a href="http://linli580.com">邻里邦</a> All rights reserved.
 */
package com.linlibang.pay.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

/**
 * 数据Entity类
 *
 * @author ThinkGem
 * @version 2014-05-16
 */
@Getter
@Setter
public abstract class DataEntity<T> extends BaseEntity<T> {

    private static final long serialVersionUID = 1L;
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    protected String remarks;    // 备注
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    protected String createBy;    // 创建者
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    protected Date createDate=new Date();    // 创建日期
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    protected String updateBy;    // 更新者
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    protected Date updateDate=new Date();    // 更新日期
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    protected String delFlag = DEL_FLAG_NORMAL;    // 删除标记（0：正常；1：删除；2：审核）


    public DataEntity() {
        super();
        this.delFlag = DEL_FLAG_NORMAL;
    }

    public DataEntity(String id) {
        super(id);
    }

    /**
     * 插入之前执行方法，需要手动调用
     * 设置创建、更新的作者
     */
    @Override
    public void preInsert() {
        // 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
        if (!this.isNewRecord) {
            setId(UUID.randomUUID().toString().replaceAll("-", ""));
        }
        this.updateDate = new Date();
        this.createDate = this.updateDate;
    }


    /**
     * 更新之前执行方法，需要手动调用
     */
    @Override
    public void preUpdate(){
        this.updateDate = new Date();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, editor);
    }


    public void setUUID(){
        if (!this.isNewRecord) {
            setId(UUID.randomUUID().toString().replaceAll("-", ""));
        }
    }
}
