package com.linlibang.pay.config;

import com.linlibang.pay.config.filters.DecryptFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * 过滤器配置文件
 *
 * @author 肥美的洪洪哥
 * @version 2019-11-11
 */
//@Configuration
public class FilterConfig {

    @Bean
    public DecryptFilter getDecryptFilter() {
        return new DecryptFilter();
    }

    @Bean
    public FilterRegistrationBean decryptFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(getDecryptFilter());
        filterRegistrationBean.addUrlPatterns("/c2b/*");
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return filterRegistrationBean;
    }


}
