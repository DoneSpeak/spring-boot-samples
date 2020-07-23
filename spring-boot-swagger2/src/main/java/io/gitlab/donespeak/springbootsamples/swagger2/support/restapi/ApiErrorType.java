package io.gitlab.donespeak.springbootsamples.swagger2.support.restapi;

import org.springframework.http.HttpStatus;

/**
 * @author DoneSpeak
 */
public enum ApiErrorType {
    /**
     * 成功操作。
     */
    OK(HttpStatus.OK),
    /**
     * 客户端严重错误，比如提交了不合法的json内容
     *
     * 需要客户端开发人员检查是否正确调用接口。
     */
    BAD_REQUEST(HttpStatus.BAD_REQUEST),
    /**
     * 业务处理错误。
     *
     * 将返回的消息提示给用户。
     */
    FAILED_PRECONDITION(HttpStatus.BAD_REQUEST),
    /**
     * 当没有提供或提供的认证无效。
     *
     * 需要用户登录或者重新登录。
     */
    UNAUTHENTICATED(HttpStatus.UNAUTHORIZED),
    /**
     * 当认证成功但是认证用户无权访问该资源时，或者由于其他安全原因而被禁止时。
     *
     * 客户端进行提示，告知用户无法操作。
     */
    FORBIDDEN(HttpStatus.FORBIDDEN),
    /**
     * 当一个不存在的资源被请求时，比如请求一个不存在的用户的信息。
     *
     * 请求的资源/路径不存在，此时客户端可以跳转到404页面。
     */
    NOT_FOUND(HttpStatus.NOT_FOUND),
    /**
     * 提交方法不支持。
     *
     * 需要客户端开发检查是否正确调用接口。
     */
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED),
    /**
     * Accept 不支持。
     *
     * 需要客户端开发检查是否正确调用接口。
     */
    NOT_ACCEPTABLE(HttpStatus.NOT_ACCEPTABLE),
    /**
     * 请求中包含了不正确的内容类型，比如提交contentType=application/xml到仅支持json的接口
     *
     * 需要客户端开发检查是否正确调用接口。
     */
    UNSUPPORTED_MEDIA_TYPE(HttpStatus.UNSUPPORTED_MEDIA_TYPE),
    /**
     * 数据校验不通过。
     *
     * 需要客户端检查输入内容是否正确或者是否存在遗漏字段。
     */
    INVALID_ARGUMENT(HttpStatus.UNPROCESSABLE_ENTITY),
    /**
     * 客户请求过于频繁。
     *
     * 需要稍后再尝试
     */
    TOO_MANY_REQUESTS(HttpStatus.TOO_MANY_REQUESTS),
    /**
     * 服务内部错误。
     *
     * 需要技术人员干预才能解决。
     */
    INTERNAL(HttpStatus.INTERNAL_SERVER_ERROR),
    /**
     * 服务器端临时不可用，比如频繁请求第三方资源而要求稍后才能使用。
     *
     * 可稍后重试，如果服务器可用了则会成功。
     */
    UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE);

    /**
     * 类型对应的Http状态码
     */
    private final HttpStatus httpStatus;

    ApiErrorType(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public HttpStatus httpStatus() {
        return httpStatus;
    }
}
