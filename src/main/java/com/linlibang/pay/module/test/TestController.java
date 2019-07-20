package com.linlibang.pay.module.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.linlibang.common.api.BaseResponse;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 * 日期: 2018/11/30--14:51
 *
 * @author yanpeicai
 */
@RestController
@RequestMapping("/test")
@Log4j
public class TestController {

	@ModelAttribute
	public JSONObject test1(){
		log.info("come on");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("buildingCode","ych");
		jsonObject.put("buildingName","1234");
		jsonObject.put("sex","nan");
		return jsonObject;
	}


	@GetMapping("/foo")
	public BaseResponse foo(@ModelAttribute TestEntity testEntity){
		log.info(JSON.toJSONString(testEntity));
		return new BaseResponse();
	}
}
