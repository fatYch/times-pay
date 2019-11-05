package com.linlibang.pay.config;

import com.linlibang.pay.config.trace.CustomTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述:
 * 日期: 2018/7/12--08:42
 *
 * @author yanpeicai
 */
@Configuration
public class ActuateConfig {

    @Bean
    public CustomTraceRepository customTraceRepository() {
        CustomTraceRepository customTraceRepository = new CustomTraceRepository();
        customTraceRepository.setCapacity(100);
        return customTraceRepository;
    }

}
