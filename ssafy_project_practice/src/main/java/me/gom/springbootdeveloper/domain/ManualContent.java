package me.gom.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ManualContent {

    // 콘텐츠 아이디 - 자동 생성, 기본키
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manualContentId", updatable = false)
    private Long manualContentId;


    // Manual과의 ManyToOne 관계 설정 (여러 콘텐츠가 하나의 메뉴얼에 속함)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manualId", nullable = false, foreignKey = @ForeignKey(name = "fk_manual_id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Manual manual;

    // 요청 상태 (텍스트, 비디오, 동영상)
    @Enumerated(EnumType.STRING)
    @Column(name = "contentType", nullable = false)
    private ContentType contentType;

    // 텍스트인 경우 직접 저장
    @Lob
    @Column(name = "content")
    private String content;

    // S3에 저장된 파일의 경로
    @Column(name = "s3Key", length = 100)
    private String s3Key;

    // S3 파일 접근 주소
    @Column(name = "s3Url", length = 100)
    private String s3Url;

    // 콘텐츠 표시 순서
    @Column(name = "sequence")
    private Integer sequence;

    // 콘텐츠 타입을 나타내는 ENUM
    public enum ContentType {
        TEXT, VIDEO, IMAGE
    }

    // 콘텐츠 업데이트 메서드
    public void updateContent(String content, String s3Key, String s3Url, Integer sequence) {
        this.content = content;
        this.s3Key = s3Key;
        this.s3Url = s3Url;
        this.sequence = sequence;
    }
}