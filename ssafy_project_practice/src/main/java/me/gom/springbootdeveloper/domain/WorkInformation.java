// WorkInformation.java
package me.gom.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkInformation {

    // 스케줄 고유 ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;


    // WorkInformation - store 관계 -> 다 대 1 : 여러개의 근무 정보가 하나의 지점에 연결 가능
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeId", nullable = false, foreignKey = @ForeignKey(name = "fk_store_id_work_info"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Store store;

    // 근무정보 - 사용자 관계 -> 다 대 1
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", nullable = false,  foreignKey = @ForeignKey(name = "fk_user_id_work_info"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;    // 근무자
    private LocalDate workDate;    // 근무 날짜
    private LocalTime startTime;    // 시작 시간
    private LocalTime endTime;    // 마감 시간
    private Boolean isVacant;    // 공석 여부
    private LocalTime checkInTime;    // 알바생 출근 시간
    private LocalTime checkOutTime;    // 알바생 퇴근 시간

    // 실제 근무자 - 대타 파악 용도
    // user: 원래 근무 배정자(외래키),  realTimeWorker: 실제 근무자
    // user == realTimeWorker: 배정 받은 사람이 근무함, user != realTimeWorker: 대타 받음
    private Long realTimeWorker;
}