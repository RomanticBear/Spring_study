// User.java
package me.gom.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    // 사용자 고유 ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    // 사용자 - 상점 관계 -> 다 대 1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeId", nullable = false, foreignKey = @ForeignKey(name = "fk_store_id_user"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Store store;

//    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//    private List<Manual> manuals;

    // 직책
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;  // ENUM('manager', 'staff')

    // 사번
    private Long accountId;

    // 비밀번호
    @Column(nullable = false, length = 30)
    private String password;

    // 이름
    @Column(nullable = false, length = 100)
    private String userName;

    // 휴대폰 번호
    @Column(length = 13)
    private String phoneNumber;

    // 이메일
    @Column(length = 100)
    private String email;

    // 우편번호
    private int zipCode;

    // 도로명 주소
    @Column(length = 255)
    private String roadAddress;

    // 상세 주소
    @Column(length = 255)
    private String detailedAddress;

    // 사업자 번호
    @Column(length = 12)
    private String businessNumber;

    public enum Role {
        MANAGER, STAFF
    }
}