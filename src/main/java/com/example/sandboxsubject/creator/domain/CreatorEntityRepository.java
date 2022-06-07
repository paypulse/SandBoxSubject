package com.example.sandboxsubject.creator.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CreatorEntityRepository
        extends JpaRepository<CreatorEntity, Integer>, JpaSpecificationExecutor<CreatorEntity>
{



}
