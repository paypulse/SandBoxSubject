package com.example.sandboxsubject.channel.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.sql.Timestamp;
import java.util.List;

public interface ChannelEntityRepository
        extends JpaRepository<ChannelEntity, Integer>, JpaSpecificationExecutor<ChannelEntity>
{
    //channel이 존재 하는지
    Integer countByChanelId(String chanelId);

    //특정 채널의 정보
    List<ChannelEntity> findByChanelId(String channelId);

    //월별 sandbox 요율
    List<ChannelEntity> findByCreateDateGreaterThanEqualAndCreateDateLessThan(Timestamp startDate, Timestamp endDate);






}
