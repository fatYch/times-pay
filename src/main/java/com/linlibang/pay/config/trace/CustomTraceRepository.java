package com.linlibang.pay.config.trace;

import lombok.extern.log4j.Log4j;
import org.springframework.boot.actuate.trace.InMemoryTraceRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 描述: trace 日志重写,存储在memory
 * 日期: 2018/7/11--17:37
 *
 * @author yanpeicai
 */
@Log4j
public class CustomTraceRepository extends InMemoryTraceRepository {

    private static final List<String> ACTUATOR_POINT = Arrays.asList(
            "/logfile", "/metrics", "/env", "/loggers", "/jolokia", "/dump", "/auditevents", "/liquibase",
            "/flyway", "/heapdump", "/trace", "/health", "/info",
            "/webjars/springfox-swagger-ui",
            "/swagger-resources",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/favicon.ico"
    );

    @Override
    public void add(Map<String, Object> map) {
        String path = map.get("path").toString();

        for (String s : ACTUATOR_POINT) {
            if (path.contains(s)) {
                return;
            }
        }

        super.add(map);
    }

}
