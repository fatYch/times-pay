package com.linlibang.pay.base;

import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 描述:
 * 日期: 2018/7/20--10:58
 *
 * @author yanpeicai
 */
@SuppressWarnings({"WeakerAccess", "SameParameterValue", "SpringJavaInjectionPointsAutowiringInspection"})
@Service
public abstract class BaseService<T extends Mapper<S>, S extends DataEntity<S>> {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	protected final static int DEFAULT_PAGE_SIZE = 20;

	@Autowired
	protected T dao;

	
	protected List<S> findListByPage(List<S> list, int pageNum) {
		return findListByPage(list, pageNum, DEFAULT_PAGE_SIZE);
	}

	protected List<S> findListByPage(List<S> list, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return list;
	}

}
