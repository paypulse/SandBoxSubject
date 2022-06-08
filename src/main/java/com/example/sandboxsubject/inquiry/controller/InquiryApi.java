package com.example.sandboxsubject.inquiry.controller;


import com.example.sandboxsubject.common.response.CommonRes;
import com.example.sandboxsubject.inquiry.service.InquiryService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@NoArgsConstructor
@RestController
public class InquiryApi {

    private InquiryService inqueryService;

    @Autowired
    public void setDataService(InquiryService inqueryService){
        this.inqueryService = inqueryService;
    }

    /**
     * 특정 채널 수익 금약과 계약 정보에 따른 크리에이터 정산 금액 조회
     * 조건 : chanelId , 기간별(월)
     * */
    @PostMapping("/api/inquery/income/creator")
    public ResponseEntity<CommonRes> getIncomeCreator(@RequestParam String channelId, @RequestParam String date) throws Exception{

        HashMap<String, Object> result = inqueryService.incomeInquery(channelId, date);

         return ResponseEntity.ok(CommonRes.builder()
                .data(result)
                .status("SUCCESS")
                .msg("조회 성공")
                .build());
    }

}

