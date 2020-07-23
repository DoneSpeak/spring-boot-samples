package io.gitlab.donespeak.springbootsamples.swagger2.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Guanrong Yang
 */
@Slf4j
@RestController
@RequestMapping("/date-tmp")
public class DateTmpController {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("")
    public DateCarry get(DateCarry dateCarry) {
        print(dateCarry);
        return dateCarry;
    }

    @GetMapping("/one")
    public DateCarry get1() {

        DateCarry dateCarry = new DateCarry();
        dateCarry.setDate(new Date());
        dateCarry.setLocalDate(LocalDate.now());
        dateCarry.setLocalTime(LocalTime.now());
        dateCarry.setLocalDateTime(LocalDateTime.now());
        print(dateCarry);
        return dateCarry;
    }

    private void print(DateCarry dateCarry) {
        StringBuilder sb = new StringBuilder("\n\n");
        String content = null;
        try {
            content = objectMapper.writeValueAsString(dateCarry);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        sb.append(content);
        log.info(sb.toString());
    }

    @GetMapping("/{localDate}/{localDateTime}/{localTime}")
    public DateCarry get2(@PathVariable LocalDate localDate, @PathVariable LocalDateTime localDateTime,
        @PathVariable LocalTime localTime) {
        DateCarry dateCarry = new DateCarry();
        dateCarry.setLocalDate(localDate);
        dateCarry.setLocalDateTime(localDateTime);
        dateCarry.setLocalTime(localTime);
        print(dateCarry);
        return dateCarry;
    }

    @GetMapping("/params")
    public DateCarry get3(@RequestParam LocalDate localDate, @RequestParam LocalDateTime localDateTime,
        @RequestParam LocalTime localTime) {
        DateCarry dateCarry = new DateCarry();
        dateCarry.setLocalDate(localDate);
        dateCarry.setLocalDateTime(localDateTime);
        dateCarry.setLocalTime(localTime);
        print(dateCarry);
        return dateCarry;
    }

    @PostMapping("")
    public DateCarry post(@RequestBody DateCarry dateCarry) {
        print(dateCarry);
        return dateCarry;
    }

    @PutMapping("")
    public DateCarry put(@RequestBody DateCarry dateCarry) {
        print(dateCarry);
        return dateCarry;
    }

    @Data
    public static class DateCarry {
        private Date date;
        private LocalDate localDate;
        private LocalDateTime localDateTime;
        private LocalTime localTime;
    }
}
