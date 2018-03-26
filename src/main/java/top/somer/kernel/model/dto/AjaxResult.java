package top.somer.kernel.model.dto;

/**
 * @author Somer
 * @date 2017/10/12
 */

public class AjaxResult {

    private String code;

    private String message;

    private Object data;

    public AjaxResult() {
    }

    public AjaxResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public AjaxResult(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
}
