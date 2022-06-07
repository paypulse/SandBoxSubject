package com.example.sandboxsubject.channel.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ChannelEntity {
    //pk
    @Id
    private String chanelId;

    //채널 명
    @Column(nullable = false)
    private String chanelName;

    //채널 생성 일자.
    @Column
    private Timestamp createDate;




}
