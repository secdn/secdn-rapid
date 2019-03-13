package com.secdn.secdnrapid.common.exception;

import com.secdn.secdnrapid.common.utils.MessageVoUtil;
import com.secdn.secdnrapid.common.vo.MessageVo;
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
	public MessageVo handleSException(SException e){
		MessageVo messageVo = MessageVoUtil.getMessageVo();
		messageVo.setErrorCode(e.getCode());
		messageVo.setErrorMsg(e.getMessage());

		return messageVo;
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public MessageVo handlerNoFoundException(Exception e) {
		logger.error(e.getMessage(), e);
		return MessageVoUtil.fail(404, "路径不存在，请检查路径是否正确");
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public MessageVo handleDuplicateKeyException(DuplicateKeyException e){
		logger.error(e.getMessage(), e);
		return MessageVoUtil.fail("数据库中已存在该记录");
	}

//	@ExceptionHandler(AuthorizationException.class)
//	public MessageVo handleAuthorizationException(AuthorizationException e){
//		logger.error(e.getMessage(), e);
//		return MessageVoUtil.fail("没有权限，请联系管理员授权");
//	}

	@ExceptionHandler(Exception.class)
	public MessageVo handleException(Exception e){
		logger.error(e.getMessage(), e);
		return MessageVoUtil.fail(e.getMessage());
	}
}
