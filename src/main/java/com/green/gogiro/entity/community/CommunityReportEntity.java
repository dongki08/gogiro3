package com.green.gogiro.entity.community;

import com.green.gogiro.entity.CreatedAtEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_report")
public class CommunityReportEntity extends CreatedAtEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long ireport;

    @Column(length = 30, nullable = false)
    private String report;
}
