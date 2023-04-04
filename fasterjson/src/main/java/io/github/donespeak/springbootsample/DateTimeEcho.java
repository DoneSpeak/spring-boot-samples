package io.github.donespeak.springbootsample;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DateTimeEcho {
    private LocalDate now;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy/MM/dd", timezone = "US/Central", locale = "US")
    private LocalDate yesterday;
    @JsonFormat(pattern = "MM/dd/yyyy", locale = "zh", timezone = "GMT+8")
    private LocalDate tomorrow;
}
