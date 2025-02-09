package me.gom.springbootdeveloper.Service;

import lombok.RequiredArgsConstructor;
import me.gom.springbootdeveloper.domain.WorkInformation;
import me.gom.springbootdeveloper.domain.User;
import me.gom.springbootdeveloper.dto.ManagerWorkInformationResponse;
import me.gom.springbootdeveloper.repository.WorkInformationRepository;
import me.gom.springbootdeveloper.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ManagerWorkInformationService {

    private final WorkInformationRepository workInformationRepository;
    private final UserRepository userRepository;

    // 1. 특정 가게에서 공석(isVacant=True)인 근무 정보 조회 (미래 근무만)
    public List<ManagerWorkInformationResponse> getVacantWorkInformation(Long storeId) {
        return workInformationRepository.findAll().stream()
                .filter(info -> info.getStore() != null && info.getStore().getStoreId().equals(storeId)) // 특정 가게
                .filter(WorkInformation::getIsVacant) // 공석 여부
                .filter(info -> LocalDateTime.now().isBefore(info.getWorkDate().atTime(info.getEndTime()))) // 현재 시간 이후의 근무만 필터링 (미래)
                .map(ManagerWorkInformationResponse::new)
                .collect(Collectors.toList());
    }

    // 2. 우리 가게 알바생이 대타 근무한 경우 (과거 근무만)
    public List<ManagerWorkInformationResponse> getInternalSubstitutes(Long storeId) {
        // 해당 가게에 속한 직원들의 userId 리스트 가져오기
        List<Long> storeUserIds = userRepository.findAll().stream()
                .filter(user -> user.getStore() != null && user.getStore().getStoreId().equals(storeId)) // 해당 가게의 직원 목록 가져오기
                .map(User::getUserId)
                .collect(Collectors.toList());

        return workInformationRepository.findAll().stream()
                .filter(info -> info.getStore() != null && info.getStore().getStoreId().equals(storeId)) // 특정 가게
                .filter(info -> info.getRealTimeWorker() != null) // 실시간 근무자가 존재하는 경우
                .filter(info -> storeUserIds.contains(info.getRealTimeWorker())) // 우리 가게 알바생이 대타 근무한 경우
                .filter(info -> !info.getUser().getUserId().equals(info.getRealTimeWorker())) // 본인이 아닌 경우
                .filter(info -> LocalDateTime.now().isAfter(info.getWorkDate().atTime(info.getEndTime()))) // 현재 시간 이전의 근무만 필터링 (과거)
                .map(ManagerWorkInformationResponse::new)
                .collect(Collectors.toList());
    }

    // 3. 외부 알바생이 우리 가게에서 대타 근무한 경우 (과거 근무만)
    public List<ManagerWorkInformationResponse> getExternalSubstitutes(Long storeId) {
        // 해당 가게에 속한 직원들의 userId 리스트 가져오기
        List<Long> storeUserIds = userRepository.findAll().stream()
                .filter(user -> user.getStore() != null && user.getStore().getStoreId().equals(storeId)) // 해당 가게의 직원 목록 가져오기
                .map(User::getUserId)
                .collect(Collectors.toList());

        return workInformationRepository.findAll().stream()
                .filter(info -> info.getStore() != null && info.getStore().getStoreId().equals(storeId)) // 특정 가게
                .filter(info -> info.getRealTimeWorker() != null) // 실시간 근무자가 존재하는 경우
                .filter(info -> !storeUserIds.contains(info.getRealTimeWorker())) // 외부 알바생이 대타 근무한 경우
                .filter(info -> LocalDateTime.now().isAfter(info.getWorkDate().atTime(info.getEndTime()))) // 현재 시간 이전의 근무만 필터링 (과거)
                .map(ManagerWorkInformationResponse::new)
                .collect(Collectors.toList());
    }
}
