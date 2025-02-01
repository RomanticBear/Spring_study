package me.gom.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    @Column(nullable = false, length = 30)
    private String storeName;

    @Column(nullable = false, length = 30)
    private String franchiseName;

    @Column(nullable = false, length = 5)
    private int zipCode;

    @Column(nullable = false, length = 255)
    private String roadAddress;

    @Column(nullable = false, length = 255)
    private String detailedAddress;

//    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//    private List<Manual> manuals;
}
