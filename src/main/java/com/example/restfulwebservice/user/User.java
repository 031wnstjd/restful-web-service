package com.example.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
//@JsonIgnoreProperties(value={"password", "ssn"}) // 지정한 필드를 JSON 데이터 반환 시 제외함
@JsonFilter("UserInfo")
public class User {
    private Integer id;

    @Size(min = 2, message = "Name은 2글자 이상 입력해주세요.")
    private String name;
    @Past
    private Date joinDate;

    private String password;
    private String ssn;
}
