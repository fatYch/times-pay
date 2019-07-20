package com.linlibang.pay.module.test;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 描述:
 * 日期: 2018/9/12--11:57
 *
 * @author yanpeicai
 */
public interface BaseTestDao<T> extends Mapper<T> {

	List<T> getAllBuilding();

	void insertBuilding(T testEntity);
}
