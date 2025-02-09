package me.gom.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.gom.springbootdeveloper.Service.ManagerWorkInformationService;
import me.gom.springbootdeveloper.dto.ManagerWorkInformationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/manager")
public class ManagerWorkInformationApiController {

    private final ManagerWorkInformationService managerWorkInformationService;

    // 현재 시간 확인 - 테스트용
    @GetMapping("/system-time")
    public ResponseEntity<String> getSystemTime() {
        return ResponseEntity.ok("Current System Time: " + LocalDateTime.now());
    }

    // 1. 특정 가게에서 공석 조회
    @GetMapping("/vacant/{storeId}")
    public ResponseEntity<List<ManagerWorkInformationResponse>> getVacantWorkInformation(@PathVariable Long storeId) {
        return ResponseEntity.ok(managerWorkInformationService.getVacantWorkInformation(storeId));
    }

    // 2. 우리 가게 알바생이 대타한 경우
    @GetMapping("/internal-substitutes/{storeId}")
    public ResponseEntity<List<ManagerWorkInformationResponse>> getInternalSubstitutes(@PathVariable Long storeId) {
        return ResponseEntity.ok(managerWorkInformationService.getInternalSubstitutes(storeId));
    }

    // 3. 외부 알바생이 우리 가게에서 대타한 경우
    @GetMapping("/external-substitutes/{storeId}")
    public ResponseEntity<List<ManagerWorkInformationResponse>> getExternalSubstitutes(@PathVariable Long storeId) {
        return ResponseEntity.ok(managerWorkInformationService.getExternalSubstitutes(storeId));
    }
}
