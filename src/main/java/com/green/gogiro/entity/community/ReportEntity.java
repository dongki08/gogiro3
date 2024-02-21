package com.green.gogiro.entity.community;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_report")
public class ReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long ireport;

    @Column(length = 30, nullable = false)
    private String report;

}
