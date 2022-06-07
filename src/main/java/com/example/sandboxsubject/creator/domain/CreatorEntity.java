package com.example.sandboxsubject.creator.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class CreatorEntity {

    //pk
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer creatorId;

    //channel ID
    @Column(nullable = false)
    private String channelId;

    //creator 명
    @Column(nullable = false)
    private String creatorNm;

    //계약 일자.
    @Column
    private Timestamp registDate;






}
