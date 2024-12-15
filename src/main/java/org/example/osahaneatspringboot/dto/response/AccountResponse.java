package org.example.osahaneatspringboot.dto.response;

import static org.example.osahaneatspringboot.constant.pattern.DateTimePatterns.DATE_TIME_PATTERN;
import static org.example.osahaneatspringboot.constant.pattern.DateTimePatterns.LOCAL_DATE_PATTERN;

import java.time.LocalDate;
import java.util.Date;

import org.example.osahaneatspringboot.entity.Role;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountResponse {
    String id;
    String username;
    Role role;
    String fullName;
    boolean gender;
    String avatar;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LOCAL_DATE_PATTERN)
    LocalDate dateOfBirth;

    String address;

    @JsonFormat(pattern = DATE_TIME_PATTERN)
    private Date createAt;

    @JsonFormat(pattern = DATE_TIME_PATTERN)
    private Date updateAt;
}
