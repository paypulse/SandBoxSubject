package com.example.sandboxsubject.register.controller.req;

import com.example.sandboxsubject.creator.domain.CreatorEntity;
import com.sun.istack.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class registerRequest  {

    //채널 ID
    private String chanelId;
    //채널 명
    private String chanelName;

    //채널 생성 일자.
    private String createChannelAt;

    //채널의 요율
    private String channelRs;
    //sandbox RS 요율
    private String sandboxRs;

    //크리에이터 들
    private List<CreatorEntity> creators;




}
