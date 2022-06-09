package com.example.sandboxsubject.inquiry.controller.res;


import com.example.sandboxsubject.channel.domain.ChannelEntity;
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
public class OnlyCreatorProfit {

    //Channel Id
    private String channelId;

    //creatorCd
    private String creatorCd;

    //creatorRs
    private BigDecimal creatorRs;

    //크리에이터가 속한 channel Info
    private List<ChannelEntity> channelInfo;

    //채널의 수익
    private List<ProfitEntity> profitInfo;

}
