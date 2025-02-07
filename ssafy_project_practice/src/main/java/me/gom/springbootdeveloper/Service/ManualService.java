// 비즈니스 계층
// 프레젠테이션 계층으로부터 전달받은 요청에 대해, 실제 서비스 로직 처리 파일

package me.gom.springbootdeveloper.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.gom.springbootdeveloper.domain.Manual;
import me.gom.springbootdeveloper.domain.Store;
import me.gom.springbootdeveloper.dto.AddManualRequest;
import me.gom.springbootdeveloper.dto.UpdateManualRequest;
import me.gom.springbootdeveloper.repository.ManualRepository;
import me.gom.springbootdeveloper.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor    // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service    // 빈으로 등록
public class ManualService {

    private final ManualRepository manualRepository;
    private final StoreRepository storeRepository;

    // 메뉴얼 추가
    public Manual save(AddManualRequest request) {
        // storeId로 Store 객체 조회
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("Store not found: " + request.getStoreId()));
        return manualRepository.save(request.toEntity(store));
    }

    // 메뉴얼 모든 데이터 조회
    public List<Manual> findAll(){
        return manualRepository.findAll();
    }

    // 메뉴얼 특정 아이디 조회
    public Manual findById(long id){
        return manualRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("not found: " + id));
    }

    // 메뉴얼 삭제 메서드
    public void delete(long id){
        manualRepository.deleteById(id);
    }


    // 메뉴얼 수정 메서드
    @Transactional
    public Manual update(long id, UpdateManualRequest request){
        Manual manual=manualRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("not found: " + id));

        manual.update(request.getManualName(), request.getCategory());
        return manual;
    }
}
