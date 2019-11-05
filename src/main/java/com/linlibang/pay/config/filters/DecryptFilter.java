package com.linlibang.pay.config.filters;


import com.alibaba.fastjson.JSON;
import com.linlibang.common.api.ApiConstant;
import com.linlibang.common.api.BaseResponse;

import com.linlibang.common.lang.StringUtils;
import com.linlibang.pay.utils.RSAUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpMethod;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Log4j
public class DecryptFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("----安全过滤器开始初始化----");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("----安全过滤器开始拦截请求----");
        //给拦截器校验是否通过
        boolean canAuth = false;
        //构建新参数
        log.info(JSON.toJSONString(servletRequest.getParameterMap()));
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String source = httpServletRequest.getHeader("timesPay-source");
        String param = httpServletRequest.getHeader("timesPay-param");

        log.info("请求方式：" + HttpMethod.POST);
        if (!httpServletRequest.getMethod().equals(HttpMethod.POST.toString())) {
            responseOutWithJson((HttpServletResponse) servletResponse, new BaseResponse(ApiConstant.FAIL, "请使用POST请求方式!", null));
            return;
        }
        if (StringUtils.isBlank(param) || StringUtils.isBlank(source)) {
            responseOutWithJson((HttpServletResponse) servletResponse, new BaseResponse(ApiConstant.PARAM_ERROR, "请求头错误!", null));
            return;
        }
        String paramJson;
        try {
            paramJson = RSAUtil.decryptByPrivateKey(param, "");
        }catch (Exception e){
            responseOutWithJson((HttpServletResponse) servletResponse, new BaseResponse(ApiConstant.PARAM_ERROR, "密钥解析失败!", null));
            return;
        }
        JsonParamRequestWrapper jsonParamRequestWrapper = new JsonParamRequestWrapper(httpServletRequest, paramJson);
        filterChain.doFilter(jsonParamRequestWrapper, servletResponse);
    }

    @Override
    public void destroy() {

    }


    /**
     * 以JSON格式输出
     *
     * @param response
     */
    protected void responseOutWithJson(HttpServletResponse response,
                                       BaseResponse baseResponse) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(JSON.toJSONString(baseResponse));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

}
