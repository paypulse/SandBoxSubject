package com.example.sandboxsubject.inquiry.service;

import com.example.sandboxsubject.channel.domain.ChannelEntity;
import com.example.sandboxsubject.channel.domain.ChannelEntityRepository;
import com.example.sandboxsubject.common.response.CommonRes;
import com.example.sandboxsubject.common.util.Utils;
import com.example.sandboxsubject.creator.domain.CreatorEntity;
import com.example.sandboxsubject.creator.domain.CreatorEntityRepository;
import com.example.sandboxsubject.inquiry.controller.req.CreatorInquiryReq;
import com.example.sandboxsubject.inquiry.controller.req.ProfiInquiryReq;
import com.example.sandboxsubject.inquiry.controller.res.CreatorPrifotRes;
import com.example.sandboxsubject.inquiry.controller.res.OnlyCreatorProfit;
import com.example.sandboxsubject.inquiry.controller.res.SandBoxProfitRes;
import com.example.sandboxsubject.profit.domain.ProfitEntity;
import com.example.sandboxsubject.profit.domain.ProfitEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class InquiryService {


    @Autowired
    private ProfitEntityRepository profitEntityRepo;

    @Autowired
    private ChannelEntityRepository channelEntityRepo;

    @Autowired
    private CreatorEntityRepository creatorEntityRepo;

    /**
     *  특정 채널 수익 금약과 계약 정보에 따른 크리에이터 정산 금약 조회
     * */
    @Transactional
    public CommonRes incomeInquery(ProfiInquiryReq request) throws Exception{

        CommonRes result = new CommonRes();
        HashMap<String, Object> data = new HashMap<>();

        Utils util  = new Utils();
        Timestamp startDate = util.stringToTimestamp(request.getStartDate());
        Timestamp endDate = util.stringToTimestamp(request.getEndDate());

        //특정 채널의 월수익
        int channelMA=0;

        //특정 채널 수익에 따른 채널 RS 금액
        int channelRSA=0;

        //특정 채널의 크리에이터에게 할당되는 금액
        int creatorRS=0;
        List<CreatorPrifotRes> creatorAmount = new ArrayList<>();


        try{
            Integer tResult = profitEntityRepo.countByChannelIdAndProfitDateGreaterThanEqualAndProfitDateLessThan(request.getChannelId(), startDate, endDate);
            if(tResult >=1){
                //채널의 수익이 존재함
                //채널의 월별 수익 금액
                List<ProfitEntity> res1 = profitEntityRepo.findByChannelIdAndProfitDateGreaterThanEqualAndProfitDateLessThan(request.getChannelId(), startDate, endDate);
                for(ProfitEntity obj:res1){
                    channelMA += obj.getProfitAmt();
                }

                //특정 채널 수익에 따른 채널 RS 금액
                BigDecimal channelMAB = new BigDecimal(channelMA);
                List<ChannelEntity> res2 = channelEntityRepo.findByChanelId(request.getChannelId());
                for(ChannelEntity obj:res2){
                    channelRSA = util.profitCalculating(channelMAB, obj.getChannelRs()) ;
                }

                //특정 채널안의 클리에이터들의 금액
                BigDecimal creatorMAB = new BigDecimal(channelRSA);
                List<CreatorEntity> res3 =creatorEntityRepo.findByChannelId(request.getChannelId());
                int indx = 0;
                for(CreatorEntity obj:res3){
                    CreatorPrifotRes chk = new CreatorPrifotRes();
                    creatorRS = util.profitCalculating(creatorMAB, obj.getCreatorRs());
                    chk.setCreateAmt(creatorRS);
                    chk.setCreatorNm(obj.getCreatorNm());

                    creatorAmount.add(indx, chk);
                    indx ++;
                }

                //특정 채널의 월 수익
                data.put("oneChannelMonthlyAmount",channelMA);
                //특정 채널의 월 RS 금액
                data.put("oneChannelMonthlyRS", channelRSA);
                //특정 채널안의 클리에이터들의 금액
                data.put("creatorsMonthlyRS", creatorAmount);

                result.setData(data);
                result.setMsg("채널 수익이 존재 .");
                result.setStatus("SUCCESS");
            }else{
                //채널 수익이 존재 하지 않음.
                result.setData(tResult);
                result.setMsg("채널 수익이 존재 하지 않습니다.");
                result.setStatus("NO PROFIT");
            }
        }catch (Exception io){
            io.printStackTrace();
        }


        return result;

    }


    /**
     *    sandbox 월별 회사 총 매출/ 순매출
     */
    @Transactional
    public CommonRes incomeSandbox(String startDate, String endDate) throws Exception{
        CommonRes result = new CommonRes();
        HashMap<String, Object> data = new HashMap<>();


        Utils util  = new Utils();
        Timestamp startD = util.stringToTimestamp(startDate);
        Timestamp endD = util.stringToTimestamp(endDate);


        //sanbox에 소속된 채널의 월별  총 매출
        int sandboxTA = 0;
        //sandbox 월별  순 매출 금액
        int sandboxRA = 0;

        List<SandBoxProfitRes> sanboxAmount = new ArrayList<>();

        try{
            Integer tRes = profitEntityRepo.countByProfitDateGreaterThanEqualAndProfitDateLessThan(startD, endD);
            if(tRes>=1){
                List<ProfitEntity> res1 = profitEntityRepo.findByProfitDateGreaterThanEqualAndProfitDateLessThan(startD, endD);
                for(ProfitEntity obj:res1){
                    sandboxTA += obj.getProfitAmt();
                }


                //월별 sandbox 순수익
                List<ChannelEntity> res2 = channelEntityRepo.findByCreateDateGreaterThanEqualAndCreateDateLessThan(startD, endD);
                for(ChannelEntity obj:res2){
                   SandBoxProfitRes chanel = new SandBoxProfitRes();
                   chanel.setChannelId(obj.getChanelId());
                   chanel.setSandBoxRs(obj.getSandBoxRs());
                   chanel.setProfitInfo(profitEntityRepo.findByChannelIdAndProfitDateGreaterThanEqualAndProfitDateLessThan(obj.getChanelId(), startD,endD));
                   sanboxAmount.add(chanel);
                }


                for(SandBoxProfitRes obj:sanboxAmount){
                    for(ProfitEntity obj2:obj.getProfitInfo()){
                        BigDecimal amt = new BigDecimal(obj2.getProfitAmt());
                        sandboxRA +=util.profitCalculating(amt, obj.getSandBoxRs());
                    }

                }


                //sandbox 총 매출 액
                data.put("sandboxTotalAmount",sandboxTA);
                data.put("sandboxRealAmount", sandboxRA);
                result.setData(data);
                result.setMsg("월 수익이 존재");
                result.setStatus("SUCCESS");


            }else{
                result.setData(tRes);
                result.setMsg("월 수익이 없습니다.");
                result.setStatus("NO PROFIT");
            }


        }catch (Exception io){
            io.printStackTrace();
        }


        return result;

    }

     /**
     *  특정 크리에이터 기준 채널별 정산 금액 조회
     *  채널 ID, 기간별 조건
     * */
    @Transactional
    public CommonRes incomeCreator(CreatorInquiryReq request) throws Exception{

        CommonRes result = new CommonRes();
        HashMap<String, Object> data = new HashMap<>();

        /*날짜*/
        Utils util = new Utils();
        Timestamp startD = util.stringToTimestamp(request.getStartDate());
        Timestamp endD = util.stringToTimestamp(request.getEndDate());

        List<OnlyCreatorProfit> creatorAmt = new ArrayList<>();
        //크리에이터 기준 채널별 총 금액
        int creatorCA=0;

        //크리에이터가 속한 채널의 순수익
        int creatorTP=0;



        //유투버가 속한 채널은 얼마나 버는지
        try{
            int tRes = creatorEntityRepo.countByCreatorCd(request.getCreatorCd());

            if(tRes >0){
                //크리에이터가 가지고 있는
                List<CreatorEntity> res = creatorEntityRepo.findByCreatorCd(request.getCreatorCd());
                for(CreatorEntity obj: res){
                    OnlyCreatorProfit creator = new OnlyCreatorProfit();
                    creator.setChannelId(obj.getChannelId());
                    creator.setCreatorCd(obj.getCreatorCd());
                    creator.setCreatorRs(obj.getCreatorRs());
                    creator.setChannelInfo(channelEntityRepo.findByChanelId(obj.getChannelId()));
                    creator.setProfitInfo(profitEntityRepo.findByChannelIdAndProfitDateGreaterThanEqualAndProfitDateLessThan(obj.getChannelId(), startD, endD ));
                    creatorAmt.add(creator);
                }

                for(OnlyCreatorProfit obj:creatorAmt){

                    if(obj.getProfitInfo() != null){
                        for(ChannelEntity cbj:obj.getChannelInfo()){
                            //크리에이터가 속한 채널의 수익
                            for(ProfitEntity obj1:obj.getProfitInfo()){
                                BigDecimal money = new BigDecimal(obj1.getProfitAmt());
                                //채널별 순수익
                                //creatorTP += util.profitCalculating(money,cbj.getChannelRs());
                                BigDecimal profit = new BigDecimal( util.profitCalculating(money,cbj.getChannelRs()));
                                creatorTP += util.profitCalculating(profit, obj.getCreatorRs());

                            }
                        }

                    }


                }

                data.put("CreatorsRealProfit", creatorTP);
                result.setData(data);
                result.setMsg("크리에이터 수익 이만치");
                result.setStatus("SUCCESS");

            }else{

                result.setData(tRes);
                result.setMsg("월 수익이 없습니다.");
                result.setStatus("NO PROFIT");
            }


        }catch (Exception io){
            io.printStackTrace();
        }



        return result;

    }




}
