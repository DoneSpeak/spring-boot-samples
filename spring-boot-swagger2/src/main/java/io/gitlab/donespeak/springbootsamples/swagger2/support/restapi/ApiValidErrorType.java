package io.gitlab.donespeak.springbootsamples.swagger2.support.restapi;

/**
 * @author Yang Guanrong
 * @date 2020/03/23
 */
public enum ApiValidErrorType {
    /**
     * 无法识别类型
     */
    UNRECOGNIZED_TYPE("unrecognized_type"),
    /**
     * 缺少必要字段
     */
    MISSING_FIELD("missing_type"),
    /**
     * 验证不通过，比如格式不匹配或者超出取值范围
     */
    INVALID("invalid"),
    /**
     * 类型不匹配
     */
    TYPE_MISMATCH("type_mismatch");

    private String type;

    ApiValidErrorType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
