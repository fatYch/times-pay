package com.linlibang.pay.config.trace;

import org.springframework.boot.actuate.trace.TraceProperties;
import org.springframework.boot.actuate.trace.WebRequestTraceFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 描述:
 * 日期: 2018/7/11--18:16
 *
 * @author yanpeicai
 */
public class RequestTraceFilter extends WebRequestTraceFilter {

	public RequestTraceFilter(CustomTraceRepository repository, TraceProperties properties) {
		super(repository, properties);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		TeeHttpServletResponse teeResponse = new TeeHttpServletResponse(response);

		filterChain.doFilter(request, teeResponse);

		teeResponse.finish();

		request.setAttribute("responseBody", teeResponse.getOutputBuffer());

		super.doFilterInternal(request, teeResponse, filterChain);
	}

	@Override
	protected Map<String, Object> getTrace(HttpServletRequest request) {
		Map<String, Object> trace = super.getTrace(request);

		byte[] outputBuffer = (byte[]) request.getAttribute("responseBody");

		if (outputBuffer!=null) {
			trace.put("responseBody", new String(outputBuffer, StandardCharsets.UTF_8));
		}

		return trace;
	}
}