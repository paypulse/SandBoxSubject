package com.example.sandboxsubject.inquiry.controller.req;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProfiInquiryReq {

    //channelId
    private String channelId;
    //시작 날짜
    private String startDate;
    //종료 날짜
    private String endDate;
}
