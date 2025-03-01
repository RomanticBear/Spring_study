// dto 계층
// 계층 간 데이터를 주고 받기 위해 사용되는 계층
// 비즈니스 로직 포함 x, 단순히 데이터를 담는 역할 수행

// dao 계층
// DB 접근을 담당하는 계층, 쿼리를 실행하고 응용 PG으로 전달
// 데이터 저장 및 조회, 비즈니스 로직과 DB로직 분리

// ManualResponse.java
package me.gom.springbootdeveloper.dto;

import lombok.Getter;
import me.gom.springbootdeveloper.domain.Manual;
import java.time.LocalDateTime;

@Getter
public class ManualResponse {
    private Long manualId;
    private Long storeId;
    private String category;
    private String manualName;
    private LocalDateTime createdTime;

    public ManualResponse(Manual manual) {
        this.manualId = manual.getManualId();
        this.storeId = manual.getStore().getStoreId();
        this.category = manual.getCategory();
        this.manualName = manual.getManualName();
        this.createdTime = manual.getCreatedTime();
    }
}
