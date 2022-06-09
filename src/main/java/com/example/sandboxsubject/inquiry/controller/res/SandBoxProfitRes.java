package com.example.sandboxsubject.inquiry.controller.res;


import com.example.sandboxsubject.profit.domain.ProfitEntity;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SandBoxProfitRes {

    //channel Id
    private String channelId;

    //sandbox Rs
    private BigDecimal sandBoxRs;


    //sandbox profitInfo
    private List<ProfitEntity> profitInfo;



}
