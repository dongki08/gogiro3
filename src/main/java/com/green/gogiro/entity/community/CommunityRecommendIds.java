package com.green.gogiro.entity.community;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@Embeddable
@EqualsAndHashCode
public class CommunityRecommendIds implements Serializable {
    private Long iboard;
    private Long iuser;
}
