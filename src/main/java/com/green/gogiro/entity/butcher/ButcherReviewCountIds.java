package com.green.gogiro.entity.butcher;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@Embeddable
@EqualsAndHashCode
public class ButcherReviewCountIds implements Serializable {
    private Long ireview;
    private Long iuser;
}
