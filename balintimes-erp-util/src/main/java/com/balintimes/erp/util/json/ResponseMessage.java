package com.balintimes.erp.util.json;

import java.util.List;

/**
 * Created by AlexXie on 2015/8/25.
 */
public class ResponseMessage {

    public static AjaxResponse successful(Object data) {
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setData(data);

        return ajaxResponse;
    }

    public static String successfulJson(Object data) {
        return JsonUtil.ToJson(successful(data));
    }

    public static AjaxResponse successful(Object data, int total) {
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setData(data);
        ajaxResponse.setTotal(total);
        return ajaxResponse;
    }

    public static String successfulJson(Object data, int total) {
        return JsonUtil.ToJson(successful(data, total));
    }

    public static String successful() {
        AjaxResponse ajaxResponse = new AjaxResponse();
        return JsonUtil.ToJson(ajaxResponse);
    }

    public static AjaxResponse successful(String message) {
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setResponseMsg(message);
        return ajaxResponse;
    }

    public static String successfulJson(String message) {
        return JsonUtil.ToJson(successful(message));
    }


    public static AjaxResponse failure() {
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setSuccess("false");
        return ajaxResponse;
    }
    public static String failureJson() {
        return JsonUtil.ToJson(failure());
    }

    /*返回一个失败的信息*/
    public static AjaxResponse failure(String message) {
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setSuccess("false");
        ajaxResponse.setResponseMsg(message);
        return ajaxResponse;
    }
    public static String failureJson(String message) {
        return JsonUtil.ToJson(failure(message));
    }

    public static String permission(boolean ispermission, String message) {

        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setPermission(ispermission);
        ajaxResponse.setResponseMsg(message);

        return JsonUtil.ToJson(ajaxResponse);

    }

    private static String _dataField = "data";

    public static <T> List<T> toObjectList(String content, Class<T> valueType) {
        String dataStr = JsonUtil.GetFieldData(content, _dataField);

        return JsonUtil.ToList(dataStr, valueType);
    }

    public static <T> T toObject(String content, Class<T> valueType) {
        String dataStr = JsonUtil.GetFieldData(content, _dataField);

        return JsonUtil.ToObject(dataStr, valueType);
    }
}

