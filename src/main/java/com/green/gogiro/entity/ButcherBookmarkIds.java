package com.green.gogiro.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@Embeddable
@EqualsAndHashCode
public class ButcherBookmarkIds implements Serializable {
    private Long iuser;
    private Long ibutcher;
}
