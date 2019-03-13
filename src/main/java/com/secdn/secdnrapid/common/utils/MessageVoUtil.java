package com.secdn.secdnrapid.common.utils;


import com.secdn.secdnrapid.common.Enum.Status;
import com.secdn.secdnrapid.common.vo.MessageVo;

public class MessageVoUtil {
    public static MessageVo getMessageVo() {
        MessageVo messageVo = new MessageVo();
        messageVo.setStatus(Status.处理失败.getStatus());
        messageVo.setTimestamp(System.currentTimeMillis());
        messageVo.setRequestId(UniqRequestIdGen.resolveReqId());
        messageVo.setErrorCode((long)0);
        messageVo.setErrorMsg("");
        return messageVo;
    }

    public static MessageVo success(){
        MessageVo messageVo = new MessageVo();
        messageVo.setRequestId(UniqRequestIdGen.resolveReqId());
        messageVo.setStatus(Status.处理成功.getStatus());
        messageVo.setTimestamp(System.currentTimeMillis());
        messageVo.setErrorCode((long)0);
        return messageVo;
    }

    public static MessageVo success(Object data,MessageVo messageVo){
        messageVo.setStatus(Status.处理成功.getStatus());
        messageVo.setData(data);
        return messageVo;
    }

    public static MessageVo success(Object data,MessageVo messageVo,String msg){
        messageVo.setStatus(Status.处理成功.getStatus());
        messageVo.setData(data);
        messageVo.setErrorMsg(msg);
        return messageVo;
    }

    public static MessageVo success(Object data,String msg){
        MessageVo messageVo = new MessageVo();
        messageVo.setStatus(Status.处理成功.getStatus());
        messageVo.setTimestamp(System.currentTimeMillis());
        messageVo.setRequestId(UniqRequestIdGen.resolveReqId());
        messageVo.setErrorCode((long)0);
        messageVo.setErrorMsg(msg);
        messageVo.setData(data);
        return messageVo;
    }

    public static MessageVo fail(String msg){
        MessageVo messageVo = new MessageVo();
        messageVo.setStatus(Status.处理失败.getStatus());
        messageVo.setTimestamp(System.currentTimeMillis());
        messageVo.setErrorCode(1000);
        messageVo.setErrorMsg(msg);
        messageVo.setRequestId(UniqRequestIdGen.resolveReqId());
        messageVo.setData(null);
        return messageVo;
    }
    public static MessageVo fail(Object code,String msg){
        MessageVo messageVo = new MessageVo();
        messageVo.setStatus(Status.处理失败.getStatus());
        messageVo.setTimestamp(System.currentTimeMillis());
        messageVo.setRequestId(UniqRequestIdGen.resolveReqId());
        messageVo.setErrorCode(code);
        messageVo.setErrorMsg(msg);
        messageVo.setData(null);
        return messageVo;
    }

    public static MessageVo fail(Object code,MessageVo messageVo,String msg){
        messageVo.setTimestamp(System.currentTimeMillis());
        messageVo.setErrorCode(code);
        messageVo.setErrorMsg(msg);
        return messageVo;
    }

    public static MessageVo success(Object data){
        MessageVo messageVo = new MessageVo();
        messageVo.setStatus(Status.处理成功.getStatus());
        messageVo.setTimestamp(System.currentTimeMillis());
        messageVo.setRequestId(UniqRequestIdGen.resolveReqId());
        messageVo.setErrorCode((long)0);
        messageVo.setData(data);
        messageVo.setErrorMsg("");
        messageVo.setData(data);
        return messageVo;
    }
}
