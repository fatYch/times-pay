package com.linlibang.pay.module.test;

import com.linlibang.pay.base.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 描述:
 * 日期: 2018/7/20--14:46
 *
 * @author yanpeicai
 */
@Getter
@Setter
@Table(name = "obj_building")
@ApiModel(description = "测试实体类")
public class TestEntity extends DataEntity<TestEntity> {

	@ApiModelProperty(value = "楼栋编码", example = "01", required = true)
	private String buildingCode;

	@ApiModelProperty(value = "楼栋名称", example = "1栋", readOnly = true)
	@Column(name = "sale_bname")
	private String buildingName;

	@ApiModelProperty(value = "性别,1男0女", allowableValues = "1,0")
	private String sex;

}
