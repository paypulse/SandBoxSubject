package com.example.sandboxsubject.profit.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface ProfitEntityRepository
            extends JpaRepository<ProfitEntity, Integer>, JpaSpecificationExecutor<ProfitEntity>
{

    //channelId로 검색
    List<ProfitEntity> findByChannelId(String channelId);

    //channelID와 날짜 조건 검색
    Integer countByChannelIdAndProfitDateEquals(String channelId, Timestamp profitDate);

    //월별 채널 수익 금액
    List<ProfitEntity> findByChannelIdAndProfitDateGreaterThanEqualAndProfitDateLessThan(String channelId, Timestamp startDate, Timestamp endDate);
    Integer countByChannelIdAndProfitDateGreaterThanEqualAndProfitDateLessThan(String channelId, Timestamp startDate, Timestamp endDate);

    //월별 총 수익
    Integer countByProfitDateGreaterThanEqualAndProfitDateLessThan(Timestamp startDate, Timestamp endDate);
    List<ProfitEntity> findByProfitDateGreaterThanEqualAndProfitDateLessThan(Timestamp startDate, Timestamp endDate);





}
