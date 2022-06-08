package com.example.sandboxsubject.profit.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.sql.Timestamp;
import java.util.List;

public interface ProfitEntityRepository
            extends JpaRepository<ProfitEntity, Integer>, JpaSpecificationExecutor<ProfitEntity>
{


    //channelID와 날짜 조건 검색
    Integer countByChannelIdAndProfitDateEquals(String channelId, Timestamp profitDate);


}
