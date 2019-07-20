package com.linlibang.pay.config.filters;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.linlibang.common.api.ApiConstant;
import com.linlibang.common.api.BaseResponse;
//import com.linlibang.common.codec.Md5Utils;
//import com.netflix.ribbon.proxy.annotation.Http;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//@Component
@Log4j
public class DecryptFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String param = httpServletRequest.getHeader("pay-auth");
        if(StringUtils.isBlank(param)){
            responseOutWithJson ((HttpServletResponse)servletResponse,new BaseResponse(ApiConstant.PARAM_ERROR));
            return;
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }


    /**
     * 以JSON格式输出
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
