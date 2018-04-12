package top.somer.kernel.model.dto;

/**
 * @author Somer
 * @date 2017/10/12
 */

public class AjaxResult {

    private Integer code;

    private String message;

    private Object data;

    public AjaxResult() {
    }

    public AjaxResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public AjaxResult(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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
