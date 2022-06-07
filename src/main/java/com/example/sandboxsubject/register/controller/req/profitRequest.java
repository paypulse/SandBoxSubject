package com.example.sandboxsubject.register.controller.req;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class profitRequest {
    //channel ID
    private String channelId;
    //수익 날짜
    private String profitCreateAt;
    //수익 금액
    private Integer profitAmt;

}
