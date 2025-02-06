package me.gom.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.gom.springbootdeveloper.Service.WorkInformationService;
import me.gom.springbootdeveloper.dto.WorkInformationSummaryResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/mypage")
public class WorkInformationApiController {

    private final WorkInformationService workInformationService;

    @GetMapping("/work-info/{userId}")
    public ResponseEntity<WorkInformationSummaryResponse> getUserWorkInformation(@PathVariable Long userId) {
        WorkInformationSummaryResponse response = workInformationService.getUserWorkInformation(userId);
        return ResponseEntity.ok(response);
    }
}
