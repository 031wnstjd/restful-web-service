package com.example.restfulwebservice.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Schema(description = "사용자 상세 정보를 위한 도메인 객체")
@Entity
@Table(name = "member") // 'user'가 db 예약어로 설정 되어 있어서 member로 테이블명 설정
public class User {

    @Id
    @GeneratedValue
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
