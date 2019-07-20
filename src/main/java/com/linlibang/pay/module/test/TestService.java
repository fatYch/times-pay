package com.linlibang.pay.module.test;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.linlibang.pay.base.BaseService;
import com.linlibang.pay.module.test.dao.TestDao;
import lombok.extern.log4j.Log4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述:
 * 日期: 2018/11/30--15:24
 *
 * @author yanpeicai
 */

@CacheConfig(cacheNames = "Test")
@Service
@Log4j
public class TestService extends BaseService<TestDao, TestEntity> {


	public List<TestEntity> getAllBuilding(int pageNum) {
		return findListByPage(dao.getAllBuilding(), pageNum);
	}

	@Cacheable
	public Page<TestEntity> getAllBuildingPage(int pageNum) {
		log.debug("get data from db");
		PageHelper.startPage(pageNum, 5, true);
		return (Page<TestEntity>) dao.getAllBuilding();
	}

}
