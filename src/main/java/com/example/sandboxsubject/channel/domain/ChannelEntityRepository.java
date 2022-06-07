package com.example.sandboxsubject.channel.domain;

import com.example.sandboxsubject.register.controller.req.registerRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ChannelEntityRepository
        extends JpaRepository<ChannelEntity, Integer>, JpaSpecificationExecutor<ChannelEntity>
{
    //channel이 존재 하는지
    Integer countByChanelId(String chanelId);

    //chnanel 값 update
    ChannelEntity findByChanelId(String chanelId);
}
