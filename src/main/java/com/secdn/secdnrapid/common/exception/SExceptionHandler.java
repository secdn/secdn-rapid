package com.secdn.secdnrapid.common.exception;


import com.secdn.secdnrapid.common.wrapper.WrapMapper;
import com.secdn.secdnrapid.common.wrapper.Wrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;


@RestControllerAdvice
public class SExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(SException.class)
	public Wrapper handleSException(SException e){
		return WrapMapper.error(e.getCode(), e.getMessage());
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public Wrapper<Object> handlerNoFoundException(Exception e) {
		logger.error(e.getMessage(), e);
		return WrapMapper.error(404, "路径不存在，请检查路径是否正确");
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public Wrapper<Object> handleDuplicateKeyException(DuplicateKeyException e){
		logger.error(e.getMessage(), e);
		return WrapMapper.error("数据库中已存在该记录");
	}

//	@ExceptionHandler(AuthorizationException.class)
//	public MessageVo handleAuthorizationException(AuthorizationException e){
//		logger.error(e.getMessage(), e);
//		return WrapMapper.error("没有权限，请联系管理员授权");
//	}

	@ExceptionHandler(Exception.class)
	public Wrapper<Object> handleException(Exception e){
		logger.error(e.getMessage(), e);
		return WrapMapper.error(e.getMessage());
	}
}
