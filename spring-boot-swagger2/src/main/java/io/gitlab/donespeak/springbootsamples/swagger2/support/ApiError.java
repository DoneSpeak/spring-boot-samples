package io.gitlab.donespeak.springbootsamples.swagger2.support;

import lombok.Data;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Set;

/**
 * @author Yang Guanrong
 * @date 2020/03/23
 */
@Data
public class ApiError {
	/**
	 * 对应的httpStatus
	 */
	private HttpStatus httpStatus;
	/**
	 * 错误编码
	 */
	private String code;
	/**
	 * 错误信息
	 */
	private String message;
	/**
	 * 数据校验错误
	 */
	private List<ValidError> validErrors;

	public ApiError() {}

	public ApiError(HttpStatus httpStatus, String code, String message) {
		this.httpStatus = httpStatus;
		this.code = code;
		this.message = message;
		this.validErrors = null;
	}

	public ApiError(HttpStatus httpStatus, String code, String message, List<ValidError> validErrors) {
		this(httpStatus, code, message);
		this.validErrors = validErrors;
	}

	public static ApiError badRequest(String code, String message) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		return new ApiError(httpStatus, code, message);
	}

	public static ApiError unauthorized() {
		HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
		String message = "The request requires authentication.";
		return new ApiError(httpStatus, httpStatus.value() + "", message);
	}

	public static ApiError forbidden() {
		HttpStatus httpStatus = HttpStatus.FORBIDDEN;
		String message = "The server understood the request but refuses to authorize it.";
		return new ApiError(httpStatus, httpStatus.value() + "", message);
	}

	public static ApiError notFound(String message) {
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		return new ApiError(httpStatus, httpStatus.value() + "", message);
	}

	public static ApiError notFound(String httpMethod, String requestUrl) {
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		String message = "No resource found for " + httpMethod + " " + requestUrl;
		return new ApiError(httpStatus, httpStatus.value() + "", message);
	}

	public static ApiError unsupportedMediaType(MediaType contentType, List<MediaType> supportedMediaTypes) {
		HttpStatus httpStatus = HttpStatus.UNSUPPORTED_MEDIA_TYPE;
		StringBuilder messageBuilder =
			new StringBuilder().append(contentType).append(" media type is not supported. Supported media types are ");

		supportedMediaTypes.forEach(t -> messageBuilder.append(t + ", "));

		String message = messageBuilder.substring(0, messageBuilder.length() - 2);

		return new ApiError(httpStatus, httpStatus.value() + "", message);
	}

	public static ApiError methodNotAllow(String method, Set<HttpMethod> supportedHttpMethods) {
		HttpStatus httpStatus = HttpStatus.METHOD_NOT_ALLOWED;
		StringBuilder messageBuilder = new StringBuilder().append(method)
			.append(" method is not supported for this request.").append(" Supported methods are ");

		supportedHttpMethods.forEach(m -> messageBuilder.append(m + ", "));

		String message = messageBuilder.substring(0, messageBuilder.length() - 2);

		return new ApiError(httpStatus, httpStatus.value() + "", message);
	}

	public static ApiError validationFailed(List<ValidError> validErrors) {
		HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
		String message = "Validation failed";
		return new ApiError(httpStatus, httpStatus.value() + "", message, validErrors);
	}

	public static ApiError tooManyRequests() {
		HttpStatus httpStatus = HttpStatus.TOO_MANY_REQUESTS;
		String message = "Too many requests";
		return new ApiError(httpStatus, httpStatus.value() + "", message);
	}

	public static ApiError internalServerError() {
		HttpStatus httpStatu = HttpStatus.INTERNAL_SERVER_ERROR;
		return new ApiError(httpStatu, httpStatu.value() + "", "System busy");
	}
}