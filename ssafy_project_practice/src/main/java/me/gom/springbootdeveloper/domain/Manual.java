package me.gom.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
public class Manual {

    // 메뉴얼 아이디 - 자동으로 생성, 기본키
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 기본키 자동으로 1씩 추가
    @Column(name="manualId",updatable = false)
    private Long manualId;

    // 메뉴얼 이름
    @Column(name="manualName",nullable = false)
    private String manualName;
    
    // 메뉴얼 내용
    @Column(name="manualContent",nullable = false)
    private String manualContent;

    // 메뉴얼 객체 생성
    @Builder // 빌더 패턴으로 객체 생성
    public Manual(String manualName, String manualContent){
        this.manualName=manualName;
        this.manualContent=manualContent;
    }

    protected Manual(){     // 기본 생성자
    }

    // 게터
    public Long getManualId(){
        return manualId;
    }

    public String getManualName(){
        return manualName;
    }

    public String getManualContent(){
        return manualContent;
    }

    // 메뉴얼 수정 메서드
    public void update(String manualName, String manualContent){
        this.manualName=manualName;
        this.manualContent=manualContent;
    }

}
