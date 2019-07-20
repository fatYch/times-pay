package com.linlibang.pay.module.test;

import com.github.pagehelper.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TestServiceTest {

	@Autowired
	private TestService service;

	@Test
	public void testGetList() {
		List<TestEntity> allBuilding = service.getAllBuilding(3);
		System.out.println(allBuilding);
	}

	@Test
	public void testGetPage() {
		for (int i = 0; i < 5; i++) {
			Page<TestEntity> allBuildingPage = service.getAllBuildingPage(4);
			System.out.println(allBuildingPage);
		}
	}
}
