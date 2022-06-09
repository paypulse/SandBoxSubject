package com.example.sandboxsubject.inquiry.controller.res;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreatorPrifotRes {
    //크리에이터 명
    private String creatorNm;
    //크리에이터 RS 금액
    private int createAmt;
}
