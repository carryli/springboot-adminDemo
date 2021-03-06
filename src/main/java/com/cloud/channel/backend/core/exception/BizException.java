package com.cloud.channel.backend.core.exception;

/**
 * @author Bruce
 * @classname BizException
 * @description 自定义业务异常
 * @date 2020/4/24 0024 18:22
 */
public class BizException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    protected int errorCode;
    /**
     * 错误信息
     */
    protected String errorMsg;

    public BizException() {
        super();
    }

    public BizException(BaseErrorInfoInterface errorInfoInterface) {
        super(errorInfoInterface.getKey() + "");
        this.errorCode = errorInfoInterface.getKey();
        this.errorMsg = errorInfoInterface.getValue();
    }

    public BizException(BaseErrorInfoInterface errorInfoInterface, Throwable cause) {
        super(errorInfoInterface.getKey() + "", cause);
        this.errorCode = errorInfoInterface.getKey();
        this.errorMsg = errorInfoInterface.getValue();
    }

    public BizException(int errorCode) {
        super(errorCode + "");
        this.errorCode = errorCode;
        this.errorMsg = ResponseCodeEnum.getValueByKey(errorCode);
    }

    public BizException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public BizException(int errorCode, String errorMsg) {
        super(errorCode + "");
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BizException(int errorCode, String errorMsg, Throwable cause) {
        super(errorCode + "", cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }

}
