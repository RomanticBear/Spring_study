// WorkInformationApiController.java (Controller)
package me.gom.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.gom.springbootdeveloper.Service.WorkInformationService;
import me.gom.springbootdeveloper.dto.WorkInformationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/mypage")
public class WorkInformationApiController {

    private final WorkInformationService workInformationService;

    @GetMapping("/work-info/{userId}")
    public ResponseEntity<List<WorkInformationResponse>> getUserWorkInformation(@PathVariable Long userId) {
        List<WorkInformationResponse> workInfoList = workInformationService.getUserWorkInformation(userId);
        return ResponseEntity.ok(workInfoList);
    }
}
