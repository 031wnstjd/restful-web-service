package com.example.restfulwebservice.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Schema(description = "사용자 상세 정보를 위한 도메인 객체")
@Entity
@Table(name = "member") // 'user'가 db 예약어로 설정 되어 있어서 member로 테이블명 설정
public class User {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
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

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public User(Integer id, String name, Date joinDate, String password, String ssn) {
        this.id = id;
        this.name = name;
        this.joinDate = joinDate;
        this.password = password;
        this.ssn = ssn;
    }

    // 연관관계 편의 메서드
    public void addPost(Post post) {
        posts.add(post);
        post.setUser(this);
    }

    public void removePost(int postId) {
        Iterator<Post> it = posts.iterator();

        while (it.hasNext()) {
            Post post = it.next();

            if (post.getId() == postId) {
                it.remove();
                return;
            }
        }
    }
}
