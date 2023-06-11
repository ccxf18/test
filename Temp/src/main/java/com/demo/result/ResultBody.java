package com.demo.result;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author admin
 */
@ApiModel(value = "响应结果")
@Data
public class ResultBody<T> implements Serializable {
    private static final long serialVersionUID = -6190689122701100762L;
    /**
     * 错误信息配置
     */
    @JSONField(serialize = false, deserialize = false)
    @JsonIgnore
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("error");
    /**
     * 响应编码
     */
    @ApiModelProperty(value = "响应编码:0-请求处理成功")
    private int code = 0;
    /**
     * 提示消息
     */
    @ApiModelProperty(value = "提示消息")
    private String message;
    /**
     * 请求路径
     */
    @ApiModelProperty(value = "请求路径")
    private String path;
    /**
     * 返回总数
     */
    @ApiModelProperty(value = "请求总数")
    private int total;
    /**
     * 响应数据
     */
    @ApiModelProperty(value = "响应数据")
    private T data;
    /**
     * http状态码
     */
    private int httpStatus;
    /**
     * 附加数据
     */
    @ApiModelProperty(value = "附加数据")
    private Map<String, Object> extra;
    /**
     * 响应时间
     */
    @ApiModelProperty(value = "响应时间")
    private long timestamp = System.currentTimeMillis();

    public ResultBody() {
        super();
    }

    public static ResultBody ok() {
        return new ResultBody().code(ErrorCode.OK.getCode()).msg(ErrorCode.OK.getMessage());
    }

    public static ResultBody failed() {
        return new ResultBody().code(ErrorCode.FAIL.getCode()).msg(ErrorCode.FAIL.getMessage());
    }

    public static <T> ResultBody<T> success() {
        return new ResultBody<T>().codeN(ErrorCode.OK.getCode()).msgN(ErrorCode.OK.getMessage());
    }

    public static <T> ResultBody<T> success(T t) {
        return new ResultBody<T>().codeN(ErrorCode.OK.getCode()).msgN(ErrorCode.OK.getMessage()).dataN(t);
    }

    public static <T> ResultBody<T> success(T t, String path) {
        return new ResultBody<T>().codeN(ErrorCode.OK.getCode()).msgN(ErrorCode.OK.getMessage()).dataN(t).pathN(path);
    }

    public static <T> ResultBody<T> success(T t, String path, Map<String, Object> extra) {
        return new ResultBody<T>().dataN(t).pathN(path).extraN(extra);
    }

    public static <T> ResultBody<T> success(String path, Map<String, Object> extra) {
        return new ResultBody<T>().codeN(ErrorCode.OK.getCode()).msgN(ErrorCode.OK.getMessage()).pathN(path).extraN(extra);
    }

    public static <T> ResultBody<T> failure() {
        return new ResultBody<T>().codeN(ErrorCode.FAIL.getCode()).msgN(ErrorCode.FAIL.getMessage());
    }

    public static <T> ResultBody<T> failure(String msg) {
        return new ResultBody<T>().codeN(ErrorCode.FAIL.getCode()).msgN(msg);
    }

    public static <T> ResultBody<T> failure(String key, String value) {
        return new ResultBody<T>().codeN(ErrorCode.FAIL.getCode()).msgN(ErrorCode.FAIL.getMessage()).putN(key, value);
    }

    public static <T> ResultBody<T> failure(String msg, String key, String value) {
        return new ResultBody<T>().codeN(ErrorCode.FAIL.getCode()).msgN(msg).putN(key, value);
    }

    public static <T> ResultBody<T> failure(String msg, Map<String, Object> extra) {
        return new ResultBody<T>().codeN(ErrorCode.FAIL.getCode()).msgN(msg).extraN(extra);
    }


    /**
     * 提示信息国际化
     *
     * @param message
     * @param defaultMessage
     * @return
     */
    @JSONField(serialize = false, deserialize = false)
    @JsonIgnore
    private static String i18n(String message, String defaultMessage) {
        return resourceBundle.containsKey(message) ? resourceBundle.getString(message) : defaultMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public int getTotal() { return total; }

    public T getData() {
        return data;
    }

    public Map<String, Object> getExtra() {
        return extra;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @JSONField(serialize = false, deserialize = false)
    @JsonIgnore
    public int getHttpStatus() {
        return httpStatus;
    }

    @JSONField(serialize = false, deserialize = false)
    @JsonIgnore
    public boolean isOk() {
        return code == ErrorCode.OK.getCode();
    }

    public ResultBody code(int code) {
        this.code = code;
        return this;
    }

    public ResultBody msg(String message) {
        this.message = i18n(ErrorCode.getResultEnum(code).getMessage(), message);
        return this;
    }

    public ResultBody data(T data) {
        this.data = data;
        return this;
    }

    public ResultBody path(String path) {
        this.path = path;
        return this;
    }

    public ResultBody total(int total) {
        this.total = total;
        return this;
    }

    public ResultBody httpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }

    public ResultBody put(String key, Object value) {
        if (extra == null) {
            extra = new HashMap<String, Object>();
        }
        extra.put(key, value);
        return this;
    }

    private ResultBody<T> codeN(int code) {
        this.code = code;
        return this;
    }

    private ResultBody<T> msgN(String message) {
        this.message = i18n(ErrorCode.getResultEnum(code).getMessage(), message);
        return this;
    }

    private ResultBody<T> dataN(T data) {
        this.data = data;
        return this;
    }

    private ResultBody<T> pathN(String path) {
        this.path = path;
        return this;
    }

    private ResultBody<T> totalN(int total) {
        this.total = total;
        return this;
    }

    private ResultBody<T> httpStatusN(int httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }

    private ResultBody<T> extraN(Map<String, Object> extra) {
        this.extra = extra;
        return this;
    }

    private ResultBody<T> putN(String key, Object value) {
        if (extra == null) {
            extra = new HashMap<String, Object>();
        }
        extra.put(key, value);
        return this;
    }

    @Override
    public String toString() {
        return "ResultBody{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", total=" + total +
                ", httpStatus=" + httpStatus +
                ", extra=" + extra +
                ", timestamp=" + timestamp +
                '}';
    }
}
