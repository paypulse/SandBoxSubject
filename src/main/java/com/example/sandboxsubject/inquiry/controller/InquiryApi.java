package com.example.sandboxsubject.inquiry.controller;


import com.example.sandboxsubject.common.response.CommonRes;
import com.example.sandboxsubject.inquiry.controller.req.CreatorInquiryReq;
import com.example.sandboxsubject.inquiry.controller.req.ProfiInquiryReq;
import com.example.sandboxsubject.inquiry.service.InquiryService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<CommonRes> getIncomeCreator(@RequestBody ProfiInquiryReq request) throws Exception{

        CommonRes result = inqueryService.incomeInquery(request);

         return ResponseEntity.ok(CommonRes.builder()
                .data(result.getData())
                .status(result.getStatus())
                .msg(result.getMsg())
                .build());
    }

    /**
     * sandbox 월별 회사 총 매출/ 순매출
     * */
    @GetMapping("/api/inquery/income/sandbox")
    public ResponseEntity<CommonRes> getIncomeSandbox(@RequestParam String startDate, @RequestParam String endDate) throws Exception{

        System.out.println(startDate);
        CommonRes result = inqueryService.incomeSandbox(startDate,endDate);
        

        return ResponseEntity.ok(CommonRes.builder()
                .data(result.getData())
                .status(result.getStatus())
                .msg(result.getMsg())
                .build());
    }

    /**
     *  특정 크리에이터 기준 채널별 정산 금액 조회
     *  채널 ID, 기간별 조건
     * */
    @PostMapping("/api/inquery/income/realCreator")
    public ResponseEntity<CommonRes> getRealCreatorIncome(@RequestBody CreatorInquiryReq request) throws Exception{

        CommonRes result = inqueryService.incomeCreator(request);


        return ResponseEntity.ok(CommonRes.builder()
                .data(result.getData())
                .status(result.getStatus())
                .msg(result.getMsg())
                .build());
    }


}

