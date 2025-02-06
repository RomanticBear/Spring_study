// ScheduleReferenceApiController.java
package me.gom.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.gom.springbootdeveloper.domain.ScheduleReference;
import me.gom.springbootdeveloper.dto.AddScheduleReferenceRequest;
import me.gom.springbootdeveloper.dto.ScheduleReferenceResponse;
import me.gom.springbootdeveloper.dto.UpdateScheduleReferenceRequest;
import me.gom.springbootdeveloper.Service.ScheduleReferenceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/schedule-references")
public class ScheduleReferenceApiController {

    private final ScheduleReferenceService scheduleReferenceService;

    @PostMapping
    public ResponseEntity<ScheduleReference> addScheduleReference(@RequestBody AddScheduleReferenceRequest request) {
        ScheduleReference savedScheduleReference = scheduleReferenceService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedScheduleReference);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleReferenceResponse>> findAllScheduleReferences() {
        List<ScheduleReferenceResponse> scheduleReferences = scheduleReferenceService.findAll()
                .stream()
                .map(ScheduleReferenceResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(scheduleReferences);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleReferenceResponse> findScheduleReference(@PathVariable long id) {
        ScheduleReference scheduleReference = scheduleReferenceService.findById(id);
        return ResponseEntity.ok(new ScheduleReferenceResponse(scheduleReference));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScheduleReference(@PathVariable long id) {
        scheduleReferenceService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleReference> updateScheduleReference(
            @PathVariable long id,
            @RequestBody UpdateScheduleReferenceRequest request) {
        ScheduleReference updatedScheduleReference = scheduleReferenceService.update(id, request);
        return ResponseEntity.ok(updatedScheduleReference);
    }
}