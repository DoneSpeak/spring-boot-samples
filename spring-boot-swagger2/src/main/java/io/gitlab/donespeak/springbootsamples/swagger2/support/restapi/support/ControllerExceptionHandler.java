package io.gitlab.donespeak.springbootsamples.swagger2.support.restapi.support;

import io.gitlab.donespeak.springbootsamples.swagger2.support.restapi.ApiError;
import io.gitlab.donespeak.springbootsamples.swagger2.support.restapi.ApiValidError;
import io.gitlab.donespeak.springbootsamples.swagger2.support.restapi.ApiValidErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ElementKind;
import javax.validation.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author Yang Guanrong
 */
@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {

    private static final String logExceptionFormat = "[EXIGENCE] Some thing wrong with the system: %s, %s";
    private Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    // =========================================
    // 数据校验

    /**
     * MethodArgumentNotValidException: 实体类属性校验不通过
     *
     * 如: listUsersValid(@RequestBody @Valid UserFilterOption option)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiError handleMethodArgumentNotValid(HttpServletRequest request, MethodArgumentNotValidException ex) {
        logger.debug(ex.getMessage(), ex);
        List<ApiValidError> apiValidErrors = validatorErrors(ex.getBindingResult(), ex);
        return validationFailed(apiValidErrors);
    }

    private ApiError validationFailed(List<ApiValidError> apiValidErrors) {
        ApiError apiError = ApiError.invalidArgument(apiValidErrors);
        return apiError;
    }

    private List<ApiValidError> validatorErrors(BindingResult result, Exception ex) {
        List<ApiValidError> apiValidErrors = new ArrayList<>();
        for (FieldError error : result.getFieldErrors()) {
            apiValidErrors.add(toFieldNotValidError(result, error, ex));
        }
        // 这里可以对接口的数据校验进行拓展
        return apiValidErrors;
    }

    /**
     * ConstraintViolationException: 直接对方法参数进行校验，校验不通过。
     *
     * 如: pageUsers(@RequestParam @Min(1)int pageIndex, @RequestParam @Max(100)int pageSize)
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ApiError handleConstraintViolationException(HttpServletRequest request, ConstraintViolationException ex) {
        List<ApiValidError> apiValidErrors = validErrors(ex);
        // 这里可以对接口的数据校验进行拓展
        return validationFailed(apiValidErrors);
    }

    /**
     * BindException: 数据绑定异常
     *
     * 效果与MethodArgumentNotValidException类似，为MethodArgumentNotValidException的父类
     */
    @ExceptionHandler(BindException.class)
    public ApiError handleBindException(HttpServletRequest request, BindException ex) {
        logger.debug(ex.getMessage(), ex);
        List<ApiValidError> apiValidErrors = validatorErrors(ex.getBindingResult(), ex);
        return validationFailed(apiValidErrors);
    }

    /**
     * 参数类型不匹配
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ApiError methodArgumentTypeMismatchExceptionHandler(HttpServletRequest request,
        MethodArgumentTypeMismatchException ex) {
        logger.debug(ex.getMessage(), ex);
        String message = "The parameter '" + ex.getName() + "' should of type '"
            + ex.getRequiredType().getSimpleName().toLowerCase() + "'";
        ApiValidError apiValidError = new ApiValidError(ApiValidErrorType.TYPE_MISMATCH.getType(), ex.getName(), message);
        // 这里可以对接口的数据校验进行拓展
        return validationFailed(Arrays.asList(apiValidError));
    }

    /**
     * 缺少必填字段
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ApiError exceptionHandle(HttpServletRequest request, MissingServletRequestParameterException ex) {
        logger.debug(ex.getMessage(), ex);
        String message = "Required parameter '" + ex.getParameterName() + "' is not present";
        ApiValidError
            apiValidError = new ApiValidError(ApiValidErrorType.MISSING_FIELD.getType(), ex.getParameterName(), message);
        // 这里可以对接口的数据校验进行拓展
        return validationFailed(Arrays.asList(apiValidError));
    }

    private ApiValidError toFieldNotValidError(ConstraintViolation<?> constraintViolation) {
        int paramIndex = 2;
        StringBuilder fieldNameBuilder = new StringBuilder();
        String paramName = "";
        for (Path.Node node : constraintViolation.getPropertyPath()) {
            if(paramIndex > 0) {
                paramIndex --;
                if(paramIndex == 0) {
                    paramName = node.getName();
                }
                continue;
            }
            if(node.isInIterable()) {
                if(node.getIndex() != null) {
                    fieldNameBuilder.append("[" + node.getIndex() + "]");
                } else {
                    String suffix = "";
                    if(node.getName().equals("<map key>")) {
                        suffix = "<K>";
                    }
                    fieldNameBuilder.append("[" + node.getKey() + "]" + suffix);
                }
            }
            if(ElementKind.CONTAINER_ELEMENT.equals(node.getKind())) {
                // 忽略即可
                continue;
            }
            fieldNameBuilder.append("." + node.getName());
        }
        String fieldName = fieldNameBuilder.length() == 0? paramName: fieldNameBuilder.toString();

        String message = constraintViolation.getMessage();
        return new ApiValidError(ApiValidErrorType.INVALID.getType(), fieldName, message);
    }

    private ApiValidError toFieldNotValidError(BindingResult result, FieldError error, Exception ex) {

        ApiValidErrorType apiValidErrorType = ApiValidErrorType.INVALID;

        String message;
        if ("typeMismatch".equals(error.getCode())) {
            message = "The parameter '" + error.getField() + "' should of type '"
                + result.getFieldType(error.getField()).getSimpleName().toLowerCase() + "'";
            apiValidErrorType = ApiValidErrorType.TYPE_MISMATCH;
        } else {
            message = error.getDefaultMessage();
        }
        String fieldName = error.getField();
        if(fieldName.endsWith("[]")) {
            fieldName = fieldName.substring(0, fieldName.length() - 2);
            fieldName = fieldName + "[" + error.getRejectedValue() + "]";
        }
        // Iterator<Path.Node> nodeIterator = error.
        // String fieldName = createFieldName();
        return new ApiValidError(apiValidErrorType.getType(), fieldName, message);
    }

    private String createFieldName(Iterator<Path.Node> iterator) {
        StringBuilder fieldNameBuilder = new StringBuilder();
        while(iterator.hasNext()) {
            Path.Node node = iterator.next();
            if(node.isInIterable()) {
                if(node.getIndex() != null) {
                    fieldNameBuilder.append("[" + node.getIndex() + "]");
                } else {
                    String suffix = "";
                    if(node.getName().equals("<map key>")) {
                        suffix = "<K>";
                    }
                    fieldNameBuilder.append("[" + node.getKey() + "]" + suffix);
                }
            }
            if(ElementKind.CONTAINER_ELEMENT.equals(node.getKind())) {
                // 忽略即可
                continue;
            }
            fieldNameBuilder.append("." + node.getName());
        }
        return fieldNameBuilder.toString();
    }

    private List<ApiValidError> validErrors(ConstraintViolationException ex) {
        List<ApiValidError> apiValidErrors = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            ApiValidError apiValidError = toFieldNotValidError(violation);
            apiValidErrors.add(apiValidError);
        }
        return apiValidErrors;
    }

    // -------------------------------

    /**
     * 返回值类型转化错误
     */
    @ExceptionHandler(HttpMessageConversionException.class)
    public ApiError exceptionHandle(HttpServletRequest request, HttpMessageConversionException ex) {
        return internalServiceError(request, ex);
    }

    /**
     * 对应 Http 请求头的 accept 客户器端希望接受的类型和服务器端返回类型不一致。 这里虽然设置了拦截，但是并没有起到作用。需要通过http请求的流程来进一步确定原因。
     */
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ApiError handleHttpMediaTypeNotAcceptableException(HttpServletRequest request,
        HttpMediaTypeNotAcceptableException ex) {
        logger.debug(ex.getMessage(), ex);
        StringBuilder messageBuilder =
            new StringBuilder().append("The media type is not acceptable.").append(" Acceptable media types are ");
        ex.getSupportedMediaTypes().forEach(t -> messageBuilder.append(t + ", "));
        String message = messageBuilder.substring(0, messageBuilder.length() - 2);

        return ApiError.notAcceptable(message);
    }

    /**
     * 对应请求头的 content-type 客户端发送的数据类型和服务器端希望接收到的数据不一致
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ApiError handleHttpMediaTypeNotSupportedException(HttpServletRequest request,
        HttpMediaTypeNotSupportedException ex) {
        logger.debug(ex.getMessage(), ex);
        return ApiError.unsupportedMediaType(ex.getContentType(), ex.getSupportedMediaTypes());
    }

    /**
     * 前端发送过来的数据无法被正常处理 比如后天希望收到的是一个json的数据，但是前端发送过来的却是xml格式的数据或者是一个错误的json格式数据
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiError handlerHttpMessageNotReadableException(HttpServletRequest request,
        HttpMessageNotReadableException ex) {
        logger.debug(ex.getMessage(), ex);
        String message = "Problems parsing JSON";
        return ApiError.badRequest(message);
    }

    /**
     * 将返回的结果转化到响应的数据时候导致的问题。 当使用json作为结果格式时，可能导致的原因为序列化错误。 目前知道，如果返回一个没有属性的对象作为结果时，会导致该异常。
     */
    @ExceptionHandler(HttpMessageNotWritableException.class)
    public ApiError handlerHttpMessageNotWritableException(HttpServletRequest request,
        HttpMessageNotWritableException ex) {
        return internalServiceError(request, ex);
    }

    /**
     * 请求方法不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ApiError exceptionHandle(HttpServletRequest request, HttpRequestMethodNotSupportedException ex) {
        logger.debug(ex.getMessage(), ex);
        return ApiError.methodNotAllow(ex.getMethod(), ex.getSupportedHttpMethods());
    }

    /**
     * 文件上传时，缺少 file 字段
     */
    @ExceptionHandler(MissingServletRequestPartException.class)
    public ApiError exceptionHandle(HttpServletRequest request, MissingServletRequestPartException ex) {
        logger.debug(ex.getMessage(), ex);
        String message = "Required request part '" + ex.getRequestPartName() + "' is not present";
        ApiError apiError = ApiError.badRequest( message);
        return apiError;
    }

    /**
     * 请求路径不存在
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ApiError exceptionHandle(HttpServletRequest request, NoHandlerFoundException ex) {
        logger.debug(ex.getMessage(), ex);
        return ApiError.notFound(ex.getHttpMethod(), ex.getRequestURL());
    }

    /**
     * 缺少路径参数 Controller方法中定义了 @PathVariable(required=true) 的参数，但是却没有在url中提供
     */
    @ExceptionHandler(MissingPathVariableException.class)
    public ApiError exceptionHandle(HttpServletRequest request, MissingPathVariableException ex) {
        String message = "Missing URI template variable '" + ex.getVariableName() + "'";
        return ApiError.badRequest(message);
    }

    /**
     * 其他所有的异常
     */
    @ExceptionHandler()
    public ApiError handleAll(HttpServletRequest request, Exception ex) {
        return internalServiceError(request, ex);
    }

    private ApiError internalServiceError(HttpServletRequest request, Exception e) {
        String errorMessage = String.format(logExceptionFormat, e.getClass().getName(), e.getMessage());
        logger.error(errorMessage, e);
        return ApiError.internalServerError();
    }

}
