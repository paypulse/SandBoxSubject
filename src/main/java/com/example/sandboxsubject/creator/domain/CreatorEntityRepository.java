package com.example.sandboxsubject.creator.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CreatorEntityRepository
        extends JpaRepository<CreatorEntity, Integer>, JpaSpecificationExecutor<CreatorEntity>
{

    //특정 채널의 크리에이터
    List<CreatorEntity> findByChannelId(String channelId);

    //크리에이터가 존재 하는지
    Integer countByCreatorCd(String creatorCd);
    List<CreatorEntity> findByCreatorCd(String creatorCd);


}
