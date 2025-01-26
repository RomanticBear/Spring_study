package me.gom.springbootdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.gom.springbootdeveloper.domain.Manual;

@NoArgsConstructor  // 기본 생성자 추가
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자 추가
@Getter

public class AddMaualRequest {

    private String manualName;
    private String manualContent;

    // toEntity: DTO를 엔티티로 만들어주는 메서드, 메뉴얼 추가 시, 저장할 엔티티로 변환하는 용도
    public Manual toEntity(){
        return Manual.builder()
                .manualName(manualName)
                .manualContent(manualContent)
                .build();
    }
}
