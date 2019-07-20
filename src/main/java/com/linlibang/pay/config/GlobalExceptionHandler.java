package com.linlibang.pay.config;

import com.linlibang.common.api.ApiConstant;
import com.linlibang.common.api.BaseResponse;
import com.linlibang.common.json.JsonUtils;
import lombok.extern.log4j.Log4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述：统一错误处理
 *
 * @author yanpeicai
 * 2018/1/29--14:46
 */
@Log4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final String CONTENT_TYPE_JSON = "application/json";
	private static final String JSON_PARAM = "json_param";

	// 捕捉其他所有异常
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.OK)
	public BaseResponse<String> globalException(Throwable ex) {
		BaseResponse<String> response = new BaseResponse<>(ApiConstant.FAIL, "系统异常,请联系管理员");
		printRequest(response);
		log.error("globalException:", ex);
		return response;
	}

	@ExceptionHandler({MissingServletRequestParameterException.class, IllegalArgumentException.class, HttpMessageNotReadableException.class, DataIntegrityViolationException.class})
	@ResponseStatus(HttpStatus.OK)
	public BaseResponse<String> paramException(Throwable ex) {
		BaseResponse<String> response = new BaseResponse<>(ApiConstant.PARAM_ERROR, "请检查参数");
		printRequest(response);
		log.error("paramException:", ex);
		return response;
	}

	@ExceptionHandler(HttpClientErrorException.class)
	@ResponseStatus(HttpStatus.OK)
	public BaseResponse<String> httpException(Throwable ex) {
		BaseResponse<String> response = new BaseResponse<>(ApiConstant.FAIL, ex.getMessage());
		printRequest(response);
		log.error("httpException:", ex);
		return response;
	}

	private void printRequest(@SuppressWarnings("unused") BaseResponse baseResponse) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		// 记录下请求内容
		log.debug("HTTP_METHOD & URL : " + request.getMethod() + " " + request.getRequestURL().toString());
//		log.debug("HTTP_METHOD : " + request.getMethod());
		log.debug("IP : " + request.getRemoteAddr());
		log.debug("Param: " + JsonUtils.getInstance().writeJson(request.getParameterMap()));

		//noinspection Duplicates
		if (CONTENT_TYPE_JSON.equals(request.getContentType())) {
			log.debug("Input Json:" + JsonUtils.getInstance().writeJson(request.getAttribute(JSON_PARAM)));
		}

		log.debug("RESPONSE: " + JsonUtils.getInstance().writeJson(request.getParameterMap()));
	}

}
