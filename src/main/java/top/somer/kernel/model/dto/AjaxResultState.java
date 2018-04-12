package top.somer.kernel.model.dto;

/**
 * @author Somer
 * @date 2017/10/12
 */

public class AjaxResultState {

    // 请求成功
    public static final Integer OK = 200;
    public static final String OK_MESSAGE = "200";

    // 请求内容错误
    public static final Integer CONTENT_ERROR = 201;
    public static final String CONTENT_ERROR_MESSAGE = "请求内容错误";

    // 内部错误
    public static final Integer INNER_ERROR = 202;
    public static final String INNER_ERROR_MESSAGE = "内部错误";

}
