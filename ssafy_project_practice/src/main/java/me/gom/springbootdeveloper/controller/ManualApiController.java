// 프레젠테이션 계층
// HTTP 요청 받고, 비즈니스 계층(ManualService)에게 요청하는 파일

package me.gom.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.gom.springbootdeveloper.Service.ManualService;
import me.gom.springbootdeveloper.domain.Manual;
import me.gom.springbootdeveloper.dto.AddMaualRequest;
import me.gom.springbootdeveloper.dto.ManualResponse;
import me.gom.springbootdeveloper.dto.UpdateManualRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController  // HTTP Request Body 객체 데이터를 JSON 형식으로 반환하는 컨트롤러
public class ManualApiController {

    private final ManualService manualService;


    // 메뉴얼 생성 controller
    @PostMapping("/api/manuals")
    public ResponseEntity<Manual> addArticle(@RequestBody AddMaualRequest request){
        Manual savedManual=manualService.save(request);

        // 요청한 자원이 성공적으로 생성되었으며 저장된 메뉴얼 정보를 응답 객체에 담아 전송
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedManual);
    }


    // 모든 메뉴얼 조회 controller
    @GetMapping("/api/manuals")
    public ResponseEntity<List<ManualResponse>> findAllManuals(){
        List<ManualResponse> manuals = manualService.findAll()
                .stream()
                .map(ManualResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(manuals);
    }

    // 특정 메뉴얼 조회 controller
    @GetMapping("/api/manuals/{id}")
    public ResponseEntity<ManualResponse> findManual(@PathVariable long id){
        Manual manual = manualService.findById(id);

        return ResponseEntity.ok()
                .body(new ManualResponse(manual));
    }


    // 메뉴얼 삭제 controller
    @DeleteMapping("/api/manuals/{id}")
    public ResponseEntity<Void> deleteManual(@PathVariable long id){
        manualService.delete(id);

        return ResponseEntity.ok()
                .build();
    }


    // 메서드 수정 controller
    @PutMapping("/api/manuals/{id}")
    public ResponseEntity<Manual> updateManual(@PathVariable long id, @RequestBody UpdateManualRequest request) {
        Manual updatedManual = manualService.update(id, request);

        return ResponseEntity.ok()
                .body(updatedManual);
    }
}
