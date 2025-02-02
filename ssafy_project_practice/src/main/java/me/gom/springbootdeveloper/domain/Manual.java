package me.gom.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;  // ✅ 추가된 import

import java.time.LocalDateTime;

@Entity
public class Manual {

    // 메뉴얼 아이디 - 자동으로 생성, 기본키
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 기본키 자동으로 1씩 추가
    @Column(name = "manualId", updatable = false)
    private Long manualId;

    // Store와의 ManyToOne 관계 설정 (여러 메뉴얼이 하나의 지점에 속함)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeId", nullable = false, foreignKey = @ForeignKey(name = "fk_store_id_manual"))
    private Store store;

    // 메뉴얼 카테고리 (예: 음료 제조, 장비, 포스기)
    @Column(name = "category", nullable = false, length = 30)
    private String category;

    // 메뉴얼 이름 (예: 아이스 아메리카노, 장비 청소 방법)
    @Column(name = "manualName", nullable = false, length = 300)
    private String manualName;

    // ✅ 메뉴얼 작성 일자 - 데이터 생성 시 자동 추가
    @CreationTimestamp
    @Column(name = "createdTime", nullable = false, updatable = false)
    private LocalDateTime createdTime;

    // 메뉴얼 객체 생성
    @Builder // 빌더 패턴으로 객체 생성
    public Manual(String manualName, String category, Store store) {
        this.manualName = manualName;
        this.category = category;
        this.store = store;
        // ✅ createdTime은 자동으로 추가되므로 별도 설정 불필요
    }

    protected Manual() {     // 기본 생성자
    }

    // 게터
    public Long getManualId() {
        return manualId;
    }

    public String getCategory() {
        return category;
    }

    public String getManualName() {
        return manualName;
    }

    public Store getStore() {
        return store;
    }

    public LocalDateTime getCreatedTime() {   // ✅ createdTime 접근 메서드
        return createdTime;
    }

    // 메뉴얼 수정 메서드
    public void update(String manualName, String category) {
        this.manualName = manualName;
        this.category = category;
    }
}
