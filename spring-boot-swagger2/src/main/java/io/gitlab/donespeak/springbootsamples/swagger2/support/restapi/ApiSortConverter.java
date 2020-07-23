package io.gitlab.donespeak.springbootsamples.swagger2.support.restapi;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 将字符串转化为sort.
 *
 * @author DoneSpeak
 */
public class ApiSortConverter {

    /**
     * 匹配：time,name | -time,name | +time,-name | ,,,time,+name,,,, 等, 不匹配：--time,name | time,+,name | time-name
     */
    private static final String SORT_QUERIES_REGEX = ",*([\\-\\+]?[a-zA-Z]+),+([\\-\\+]?[a-zA-Z]+)+,*";

    private static final String ORDER_PREFIX_ASC = "+";
    private static final String ORDER_PREFIX_DESC = "-";
    private static final String SORT_SEPARATOR = ",";

    public static List<ApiFieldSort> parse(String sortQueries) {
        if (!validate(sortQueries)) {
            throw new IllegalArgumentException(
                "The format of sortQueries is incorrect, which should look like [-time,name], [+time,name]");
        }
        String[] sortWords = StringUtils.split(sortQueries, SORT_SEPARATOR);
        List<ApiFieldSort> sorts = Arrays.stream(sortWords).filter(StringUtils::isNotBlank)
            .map(w -> wordToSort(w)).collect(Collectors.toList());
        return sorts;
    }

    private static ApiFieldSort wordToSort(String sortWord) {
        ApiFieldSort.ApiSortOrder order = ApiFieldSort.ApiSortOrder.ASC;
        String fieldName = sortWord;
        if (sortWord.startsWith(ORDER_PREFIX_DESC)) {
            order = ApiFieldSort.ApiSortOrder.DESC;
            fieldName = sortWord.substring(1);
        } else if (sortWord.startsWith(ORDER_PREFIX_ASC)) {
            order = ApiFieldSort.ApiSortOrder.ASC;
            fieldName = sortWord.substring(1);
        }
        return new ApiFieldSort(fieldName, order);
    }

    public static String toSortQueries(List<ApiFieldSort> sortBuilders) {
        if (sortBuilders == null || sortBuilders.isEmpty()) {
            return "";
        }
        return sortBuilders.stream().map(ApiSortConverter::fieldSortToWord).collect(Collectors.joining(SORT_SEPARATOR));
    }

    private static String fieldSortToWord(ApiFieldSort fieldSort) {
        if (fieldSort == null) {
            return "";
        }
        String prefix = orderToPrefix(fieldSort.getOrder());
        String fieldName = fieldSort.getField();
        return prefix + fieldName;
    }

    private static String orderToPrefix(ApiFieldSort.ApiSortOrder order) {
        return order == ApiFieldSort.ApiSortOrder.DESC ? ORDER_PREFIX_DESC : "";
    }

    public static boolean validate(String sortQueries) {
        return StringUtils.isEmpty(sortQueries) || sortQueries.matches(SORT_QUERIES_REGEX);
    }

}
