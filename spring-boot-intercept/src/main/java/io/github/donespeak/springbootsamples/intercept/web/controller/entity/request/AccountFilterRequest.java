package io.github.donespeak.springbootsamples.intercept.web.controller.entity.request;

import lombok.Data;

/**
 * @author DoneSpeak
 */
@Data
public class AccountFilterRequest {
    private String firstName;
    private String lastName;
}
