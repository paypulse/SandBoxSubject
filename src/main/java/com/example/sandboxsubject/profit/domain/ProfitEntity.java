package com.example.sandboxsubject.profit.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ProfitEntity {

    //pk
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer profitId;

    //수익 금액
    @Column(nullable = false)
    private Integer profitAmt;

    //크리에이터 ID
    @Column
    private String channelId;

    //수익 날짜
    @Column
    private Timestamp profitDate;

}
