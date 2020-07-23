package io.gitlab.donespeak.springbootsamples.swagger2.support.restapi;

import lombok.Data;

/**
 * @author DoneSpeak
 */
@Data
public class ApiFieldSort {
    private String field;
    private ApiSortOrder order;

    public ApiFieldSort() {}

    public ApiFieldSort(String field, ApiSortOrder order) {
        this.field = field;
        this.order = order;
    }

    public static ApiFieldSort asc(String field) {
        return new ApiFieldSort(field, ApiSortOrder.ASC);
    }

    public static ApiFieldSort desc(String field) {
        return new ApiFieldSort(field, ApiSortOrder.DESC);
    }

    public enum ApiSortOrder {
        ASC,
        DESC
    }

}
