package com.lecheng.protectAnimals.pojo;
public class ResponseMessage<T> {
    private int code;
    private T data;
    private String msg;

    public ResponseMessage Success(){
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        responseMessage.setCode(200);
        responseMessage.setMsg("success");
        return responseMessage;
    }
    public ResponseMessage Success(Object data){
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        responseMessage.setCode(200);
        responseMessage.setMsg("success");
        responseMessage.setData(data);
        return responseMessage;
    }
    public ResponseMessage Remind(String remind){
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        responseMessage.setCode(200);
        responseMessage.setMsg(remind);
        return responseMessage;
    }
    public ResponseMessage False(){
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        responseMessage.setCode(500);
        responseMessage.setMsg("false");
        return responseMessage;
    }
    public ResponseMessage False(String msg){
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        responseMessage.setCode(500);
        responseMessage.setMsg(msg);
        return responseMessage;
    }
    public ResponseMessage Unauthorized(){
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        responseMessage.setCode(401);
        responseMessage.setMsg("未授权");
        return responseMessage;
    }
    public ResponseMessage NotFound(){
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        responseMessage.setCode(404);
        responseMessage.setMsg("未找到");
        return responseMessage;
    }
    public ResponseMessage unLogin(){
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        responseMessage.setCode(400);
        responseMessage.setMsg("未登录");
        return responseMessage;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
