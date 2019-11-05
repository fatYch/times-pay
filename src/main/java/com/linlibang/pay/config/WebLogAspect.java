package com.linlibang.pay.config;

import com.linlibang.common.json.JsonUtils;
import lombok.extern.log4j.Log4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述: web 请求日志切面
 * 日期: 2018/8/24--16:24
 *
 * @author yanpeicai
 */
@Aspect
@Component
@Log4j
public class WebLogAspect {

    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String JSON_PARAM = "json_param";

    @Pointcut("execution(public * com.linlibang.pay.module.*.*.*Controller.*(..))")
    public void webLog() {

    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 只记录post方法
        if (CONTENT_TYPE_JSON.equals(request.getContentType()) && "POST".equals(request.getMethod())) {
            // 记录下请求内容
//			log.info("请求URL : " + form.getRequestURL());
//			log.info("请求IP : " + form.getRemoteAddr());
//			log.info("请求方法 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

            // 获取参数, 只取自定义的参数, 自带的HttpServletRequest, HttpServletResponse不管
            if (joinPoint.getArgs().length > 0) {
                for (Object o : joinPoint.getArgs()) {
                    if (o instanceof HttpServletRequest || o instanceof HttpServletResponse) {
                        continue;
                    }
//					log.info("请求参数 : " + JSON.toJSONString(o));
                    request.setAttribute(JSON_PARAM, o);
                }
            }
        }
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) {
        // 并发可能会导致顺序错乱,暂时没想到高性能处理方法,搁置
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        log.debug("HTTP_METHOD & URL : " + request.getMethod() + " " + request.getRequestURL().toString());
        log.debug("IP : " + request.getRemoteAddr());
        log.debug("Param: " + JsonUtils.getInstance().writeJson(request.getParameterMap()));

        //noinspection Duplicates
        if (CONTENT_TYPE_JSON.equals(request.getContentType())) {
            log.debug("Input Json:" + JsonUtils.getInstance().writeJson(request.getAttribute(JSON_PARAM)));
        }

        // 处理完请求，返回内容
        if (ret instanceof String) {
            log.debug("RESPONSE : " + ret);
        } else {
            log.debug("RESPONSE : " + JsonUtils.getInstance().writeJson(ret));
        }


    }
}
