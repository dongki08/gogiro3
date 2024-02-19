package com.green.gogiro.entity.community;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@Embeddable
@EqualsAndHashCode
public class CommunityFavIds implements Serializable {
    private Long iuser;
    private Long iboard;
}
