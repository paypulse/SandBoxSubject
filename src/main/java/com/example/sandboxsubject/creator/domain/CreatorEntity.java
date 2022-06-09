package com.example.sandboxsubject.creator.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class CreatorEntity {

    //pk
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Integer creatorId;

    //크리에이터 계약 번호
    @Column(nullable = false)
    private String creatorCd;

    //channel ID
    @Column(nullable = false)
    @JsonIgnore
    private String channelId;

    //creator 명
    @Column(nullable = false)
    private String creatorNm;

    //계약 일자.
    @Column
    private Timestamp registDate;

    //크리에이터별 RS
    @Column
    private BigDecimal creatorRs;




}
