package com.example.sandboxsubject.inquiry.controller.req;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreatorInquiryReq {
    //Creator Cd
    private String creatorCd;
    //날짜
    private String startDate;
    private String endDate;

}
