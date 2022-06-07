package com.example.sandboxsubject.register.controller;


import com.example.sandboxsubject.common.response.CommonRes;
import com.example.sandboxsubject.register.controller.req.profitRequest;
import com.example.sandboxsubject.register.controller.req.registerRequest;
import com.example.sandboxsubject.register.service.RegisterService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@NoArgsConstructor
@RestController
public class RegistersApi {


    private RegisterService registerService;

    @Autowired
    public void setDataService(RegisterService registerService){
        this.registerService = registerService;
    }

    /**
     * 1. 특정 유투브 채널에 수익 금액 데이터 등록
     * 수익 데이터 기준은 daily
     * */
    @PostMapping("/api/profit/register")
    public ResponseEntity<CommonRes> setProfitData(@RequestBody profitRequest request ) throws Exception{

        if(registerService.insertProfitAmt(request)){
             return ResponseEntity.ok(CommonRes.builder()
                .data("{}")
                .status("SUCCESS")
                .msg("수익 입력 완료")
                .build());
        }else{
             return ResponseEntity.ok(CommonRes.builder()
                .data("{}")
                .status("SUCCESS")
                .msg("이미 수익 입력됨 ")
                .build());
        }


    }


    /**
     * 유투브 채널과 크리에이터 계약 정보 등록
     * */
    @PostMapping("/api/contract/register")
    public ResponseEntity<CommonRes> setRegisterInfo(@RequestBody registerRequest request) throws Exception{

        System.out.println(request);

        if(registerService.insertRegisterInfo(request)){
            return ResponseEntity.ok(CommonRes.builder()
                .data("{}")
                .status("SUCCESS")
                .msg("계약 정보 입력 완료")
                .build());
        }else{
            return ResponseEntity.ok(CommonRes.builder()
                .data("{}")
                .status("FAIL")
                .msg("계약 정보 입력 불가")
                .build());
        }


    }

}
