package io.gitlab.donespeak.springbootsamples.swagger2.support.restapi;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author DoneSpeak
 */
public class ApiSortConverterTest {

    @Test
    public void parse() {
        List<ApiFieldSort> ApiFieldSortList = ApiSortConverter.parse("-time,name");
        for (ApiFieldSort sort : ApiFieldSortList) {
            if (StringUtils.equals(sort.getField(), "time")) {
                assertEquals(ApiFieldSort.ApiSortOrder.DESC, sort.getOrder());
            } else {
                assertEquals("name", sort.getField());
                assertEquals(ApiFieldSort.ApiSortOrder.ASC, sort.getOrder());
            }
        }
    }

    @Test
    public void convert() {
        String sortQueries = "-time,name";
        List<ApiFieldSort> ApiFieldSortList = Arrays.asList(ApiFieldSort.desc("time"),
            ApiFieldSort.asc("name"));
        String result = ApiSortConverter.toSortQueries(ApiFieldSortList);
        assertEquals(sortQueries, result);
    }

    @Test
    public void validate() {
        String[] shouldPass = {"name,time", "-name,time", "name,-time", "-name,-time", "name,+time", "+name,+time",
            ",,,,name,,,,,-time,,,,,"};

        String[] shouldFail = {"name-time", "- name,time", "name,-,time", "--name,time", "++name,--time"};

        for (String pass : shouldPass) {
            assertTrue(ApiSortConverter.validate(pass));
        }
        for (String fail : shouldFail) {
            assertFalse(ApiSortConverter.validate(fail));
        }
    }
}