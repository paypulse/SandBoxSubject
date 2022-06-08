package com.example.sandboxsubject.inquiry.service;

import com.example.sandboxsubject.common.util.Utils;
import com.example.sandboxsubject.profit.domain.ProfitEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class InquiryService {


    @Autowired
    private ProfitEntityRepository profitEntityRepo;


    /**
     *  특정 채널 수익 금약과 계약 정보에 따른 크리에이터 정산 금약 조회
     * */
    @Transactional
    public HashMap<String,Object> incomeInquery(String channelId, String date) throws Exception{

        HashMap<String, Object> result = new HashMap<>();

        Utils util  = new Utils();
        Timestamp time = util.stringToTimestamp(date);


        //queryDSL
        









        return result;

    }




}
