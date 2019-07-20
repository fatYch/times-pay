package com.linlibang.pay.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import lombok.extern.log4j.Log4j;
import lombok.val;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.HashMap;

/**
 * db & db pool config
 *
 * @author yanpeicai
 * @date 2018/7/3 21:15
 */
@Log4j
@MapperScan(basePackages = {"com.linlibang.pay.module.*.dao"})
@Configuration
public class DbConfig {

	/*
	 * druid 配置
	 * */
	@Bean
	public ServletRegistrationBean druidServletRegistrationBean() {
		log.info("init druidServletRegistrationBean");


		val servletRegistrationBean = new ServletRegistrationBean();
		// 使用security配置
		servletRegistrationBean.addInitParameter("allow", "127.0.0.1,10.1.39.*");
		// 账号密码
		servletRegistrationBean.addInitParameter("loginUsername", "admin");
		servletRegistrationBean.addInitParameter("loginPassword", "admin,,a");

		servletRegistrationBean.addInitParameter("resetEnable", "false");
		servletRegistrationBean.addUrlMappings("/druid/*");
		servletRegistrationBean.setServlet(new StatViewServlet());
		return servletRegistrationBean;
	}

	@Bean
	public FilterRegistrationBean duridFilterRegistrationBean() {
		log.info("init duridFilterRegistrationBean");
		val filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new WebStatFilter());
		val initParams = new HashMap<String, String>(1);
		//设置忽略请求
		initParams.put("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
		filterRegistrationBean.setInitParameters(initParams);
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
	}

	@Bean
	public WallFilter wallFilter() {
		WallFilter filter = new WallFilter();
		filter.setConfig(wallConfig());
		return filter;
	}

	@Bean
	public WallConfig wallConfig() {
		WallConfig config = new WallConfig();
		config.setMultiStatementAllow(true);
		config.setNoneBaseStatementAllow(true);
		return config;
	}

}