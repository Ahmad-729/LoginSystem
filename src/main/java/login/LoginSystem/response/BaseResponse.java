package login.LoginSystem.response;

public class BaseResponse {

    private int resultCode;
private String message;
private Object data;
private Object errorData;

    public BaseResponse() {
    }

    public BaseResponse(int resultCode, String message, Object data, Object errorData) {
        this.resultCode = resultCode;
        this.message = message;
        this.data = data;
        this.errorData = errorData;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getErrorData() {
        return errorData;
    }

    public void setErrorData(Object errorData) {
        this.errorData = errorData;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "resultCode=" + resultCode +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", errorData=" + errorData +
                '}';
    }
}
