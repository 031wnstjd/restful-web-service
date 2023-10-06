package com.example.restfulwebservice.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
//@JsonIgnoreProperties(value={"password", "ssn"}) // 지정한 필드를 JSON 데이터 반환 시 제외함
@NoArgsConstructor
//@JsonFilter("UserInfo")
@Setter @Getter
@Schema(description = "사용자 상세 정보를 위한 도메인 객체")
public class User {
    private Integer id;

    @Size(min = 2, message = "Name은 2글자 이상 입력해주세요.")
    @Schema(description = "사용자의 이름을 입력해주세요.")
    private String name;

    @Past
    @Schema(description = "사용자의 등록일을 입력해주세요.")
    private Date joinDate;

    @Schema(description = "사용자의 패스워드를 입력해주세요.")
    private String password;

    @Schema(description = "사용자의 주민등록번호를 입력해주세요.")
    private String ssn;
}
