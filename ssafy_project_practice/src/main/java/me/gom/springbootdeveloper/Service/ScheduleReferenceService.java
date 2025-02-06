// ScheduleReferenceService.java
package me.gom.springbootdeveloper.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.gom.springbootdeveloper.domain.ScheduleReference;
import me.gom.springbootdeveloper.domain.User;
import me.gom.springbootdeveloper.dto.AddScheduleReferenceRequest;
import me.gom.springbootdeveloper.dto.UpdateScheduleReferenceRequest;
import me.gom.springbootdeveloper.repository.ScheduleReferenceRepository;
import me.gom.springbootdeveloper.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ScheduleReferenceService {

    private final ScheduleReferenceRepository scheduleReferenceRepository;
    private final UserRepository userRepository;

    public ScheduleReference save(AddScheduleReferenceRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + request.getUserId()));

        return scheduleReferenceRepository.save(request.toEntity(user));
    }

    public List<ScheduleReference> findAll() {
        return scheduleReferenceRepository.findAll();
    }

    public ScheduleReference findById(long id) {
        return scheduleReferenceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ScheduleReference not found: " + id));
    }

    public void delete(long id) {
        scheduleReferenceRepository.deleteById(id);
    }

    @Transactional
    public ScheduleReference update(long id, UpdateScheduleReferenceRequest request) {
        ScheduleReference scheduleReference = scheduleReferenceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ScheduleReference not found: " + id));

        scheduleReference.setScheduleDate(request.getScheduleDate());
        scheduleReference.setScheduleStartTime(request.getScheduleStartTime());
        scheduleReference.setScheduleEndTime(request.getScheduleEndTime());

        return scheduleReference;
    }
}
