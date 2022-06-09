package com.example.sandboxsubject.register.service;

import com.example.sandboxsubject.channel.domain.ChannelEntity;
import com.example.sandboxsubject.channel.domain.ChannelEntityRepository;
import com.example.sandboxsubject.common.util.Utils;
import com.example.sandboxsubject.creator.domain.CreatorEntity;
import com.example.sandboxsubject.creator.domain.CreatorEntityRepository;
import com.example.sandboxsubject.profit.domain.ProfitEntity;
import com.example.sandboxsubject.profit.domain.ProfitEntityRepository;

import com.example.sandboxsubject.register.controller.req.profitRequest;
import com.example.sandboxsubject.register.controller.req.registerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Timestamp;


@Service
@Transactional
public class RegisterService {

    @Autowired
    private ProfitEntityRepository profitEntityRepo;

    @Autowired
    private ChannelEntityRepository chanelEntityRepo;

    @Autowired
    private CreatorEntityRepository creatorRepo;


    private BigDecimal oneHundread = new BigDecimal("1.0");


    /**
     * 수익 금액 데이터 등록
     * */
    @Transactional
    public boolean insertProfitAmt(profitRequest request) throws Exception {
        boolean result = false;

        Utils util = new Utils();
        Timestamp time = util.stringToTimestamp(request.getProfitCreateAt());

        try{

            Integer res = profitEntityRepo.countByChannelIdAndProfitDateEquals(request.getChannelId(),time);
            if(res > 0){
                result = false;
            }else{

                ProfitEntity saveProfit = new ProfitEntity();
                saveProfit.setChannelId(request.getChannelId());
                saveProfit.setProfitAmt(request.getProfitAmt());
                saveProfit.setProfitDate(time);
                profitEntityRepo.save(saveProfit);

                result = true;
            }


        }catch (Exception io){
            io.printStackTrace();
        }

        return result;

    }


    /**
     * 유투브 채널과 크리에이터 계약 정보 등록
     * */
    @Transactional
    public boolean insertRegisterInfo(registerRequest request) throws Exception{
       boolean result  = false;

        Utils util = new Utils();
        Timestamp time = util.stringToTimestamp(request.getCreateChannelAt());

        try{
            BigDecimal rsResult =util.sumRs(request.getChannelRs(), request.getSandboxRs());

            if(rsResult.equals(oneHundread)){
                //요율의 합이 100%면 등록
                Integer chenlChk = chanelEntityRepo.countByChanelId(request.getChanelId());
                BigDecimal chenelRs = new BigDecimal(request.getChannelRs());
                BigDecimal sandboxRs = new BigDecimal(request.getSandboxRs());

                if(chenlChk <=0) {

                    //채널 insert
                    ChannelEntity chanel = new ChannelEntity();
                    chanel.setChanelId(request.getChanelId());
                    chanel.setChanelName(request.getChanelName());
                    chanel.setCreateDate(time);
                    chanel.setChannelRs(chenelRs);
                    chanel.setSandBoxRs(sandboxRs);
                    chanelEntityRepo.save(chanel);
                }

                if(request.getCreators().isEmpty()){
                    result = false;
                }else{
                    //creator 등록
                    BigDecimal resultRs = new BigDecimal("0");

                    for(CreatorEntity obj:request.getCreators()){
                        resultRs = resultRs.add(obj.getCreatorRs());
                    }

                    if(resultRs.equals(oneHundread)){
                        for(CreatorEntity obj:request.getCreators()){

                            CreatorEntity creator = new CreatorEntity();
                            creator.setChannelId(request.getChanelId());
                            creator.setCreatorCd(obj.getCreatorCd());
                            creator.setCreatorNm(obj.getCreatorNm());
                            creator.setRegistDate(obj.getRegistDate());
                            creator.setCreatorRs(obj.getCreatorRs());
                            creatorRepo.save(creator);
                        }
                        result = true;

                    }else{
                        //요율 합이 100이 안됨
                        result = false;
                    }


                }

            }else{
                //등록이 되면 안됨
                result = false;

            }




        }catch (Exception io){
            io.printStackTrace();
        }



        return result;
    }







}
