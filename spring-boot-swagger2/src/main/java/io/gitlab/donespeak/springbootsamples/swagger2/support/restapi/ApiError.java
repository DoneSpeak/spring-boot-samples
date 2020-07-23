package io.gitlab.donespeak.springbootsamples.swagger2.support.restapi;

import io.gitlab.donespeak.springbootsamples.swagger2.support.restapi.entity.ResourceInfo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Yang Guanrong
 * @date 2020/03/23
 */
public class ApiError {
	/**
	 * 错误编码
	 */
	@Getter
	private String code = "";
	private ApiErrorType type;
	/**
	 * 错误信息
	 */
	@Getter
	private String message = "";
	/**
	 * 数据校验错误
	 */
	@Getter
	private List<ApiValidError> validErrors = new ArrayList<>();

	private ApiError(ApiErrorType type, String message) {
		this(type, "", message);
	}

	private ApiError(ApiErrorType type, String message, List<ApiValidError> validErrors) {
		this(type, "", message, validErrors);
	}

	private ApiError(ApiErrorType type, String code, String message) {
		this(type, code, message, new ArrayList<>());
	}

	private ApiError(ApiErrorType type, String code,
			String message, List<ApiValidError> validErrors) {
		this.type = type;
		this.code = code;
		this.message = message;
		this.validErrors = validErrors;
	}

	public int getHttpStatus() {
		return type.httpStatus().value();
	}

	public ApiErrorType type() {
		return type;
	}

	public String getType() {
		return type.name();
	}

	public static ApiError badRequest(String message) {
		return new ApiError(ApiErrorType.BAD_REQUEST, message);
	}

	public static ApiError failedPrecondition(String code, String message) {
		return new ApiError(ApiErrorType.FAILED_PRECONDITION, code, message);
	}

	public static ApiError unauthorized() {
		String message = "The request requires authentication.";
		return new ApiError(ApiErrorType.UNAUTHENTICATED, message);
	}

	public static ApiError forbidden() {
		String message = "The server understood the request but refuses to authorize it.";
		return new ApiError(ApiErrorType.FORBIDDEN, message);
	}

	public static ApiError notFound(String message) {
		return new ApiError(ApiErrorType.NOT_FOUND, message);
	}

	public static ApiError notFound(String httpMethod, String requestUrl) {
		String message = "No resource found for " + httpMethod + " " + requestUrl;
		return new ApiError(ApiErrorType.NOT_FOUND, message);
	}

	public static ApiError notFound(ResourceInfo resourceInfo) {
		String message = "Resource %s (%s) not found";
		message = String.format(message, resourceInfo.getResourceType(), resourceInfo.getResourceName());
		return new ApiError(ApiErrorType.NOT_FOUND, message);
	}

	public static ApiError unsupportedMediaType(MediaType contentType, List<MediaType> supportedMediaTypes) {
		StringBuilder messageBuilder =
			new StringBuilder().append(contentType).append(" media type is not supported. Supported media types are ");

		supportedMediaTypes.forEach(t -> messageBuilder.append(t + ", "));

		String message = messageBuilder.substring(0, messageBuilder.length() - 2);

		return new ApiError(ApiErrorType.UNSUPPORTED_MEDIA_TYPE, message);
	}

	public static ApiError methodNotAllow(String method, Set<HttpMethod> supportedHttpMethods) {
		StringBuilder messageBuilder = new StringBuilder().append(method)
			.append(" method is not supported for this request.").append(" Supported methods are ");

		supportedHttpMethods.forEach(m -> messageBuilder.append(m + ", "));

		String message = messageBuilder.substring(0, messageBuilder.length() - 2);

		return new ApiError(ApiErrorType.METHOD_NOT_ALLOWED, message);
	}

	public static ApiError notAcceptable(String message) {
		return new ApiError(ApiErrorType.NOT_ACCEPTABLE, message);
	}

	public static ApiError invalidArgument(List<ApiValidError> apiValidErrors) {
		String message = "Validation failed";
		return new ApiError(ApiErrorType.INVALID_ARGUMENT, message, apiValidErrors);
	}

	public static ApiError tooManyRequests() {
		String message = "Too many requests";
		return new ApiError(ApiErrorType.TOO_MANY_REQUESTS, message);
	}

	public static ApiError internalServerError() {
		String message = "System busy";
		return new ApiError(ApiErrorType.INTERNAL, message);
	}
}