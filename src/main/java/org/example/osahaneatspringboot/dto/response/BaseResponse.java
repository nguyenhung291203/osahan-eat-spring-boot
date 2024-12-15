package org.example.osahaneatspringboot.dto.response;

import static org.example.osahaneatspringboot.constant.pattern.DateTimePatterns.DATE_TIME_PATTERN;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseResponse {
    @JsonFormat(pattern = DATE_TIME_PATTERN)
    private Date createAt;

    @JsonFormat(pattern = DATE_TIME_PATTERN)
    private Date updateAt;
}
