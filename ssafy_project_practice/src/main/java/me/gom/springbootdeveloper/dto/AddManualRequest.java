package me.gom.springbootdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.gom.springbootdeveloper.domain.Manual;
import me.gom.springbootdeveloper.domain.Store;

import java.time.LocalDateTime;

@NoArgsConstructor  // 기본 생성자 추가
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자 추가
@Getter

public class AddManualRequest {

    private String manualName;
    private String category;
    private Long storeId;

    // toEntity
    // DTO -> 엔티티 변환: 메뉴얼 추가 시, request 입력 데이터를 엔티티로 변환하는 용도
    public Manual toEntity(Store store) {
        return Manual.builder()
                .manualName(manualName)
                .category(category)
                .store(store)  // Store 객체 연결
                .createdTime(LocalDateTime.now())  // 현재 시간 넣기
                .build();
    }
}
