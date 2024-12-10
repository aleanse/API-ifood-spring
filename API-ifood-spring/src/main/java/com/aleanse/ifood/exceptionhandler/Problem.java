package com.aleanse.ifood.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.springframework.boot.web.error.ErrorAttributeOptions;

import java.time.LocalDateTime;



@JsonInclude(JsonInclude.Include.NON_NULL) // omite um campo na serialização caso um atributo seja nulo
@Getter
@Builder
public class Problem {
    private Integer status;
    private String type;
    private String title;
    private String detail;

}
