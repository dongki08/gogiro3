package com.green.gogiro.entity.shop;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@Embeddable
@EqualsAndHashCode
public class ShopBookmarkIds implements Serializable {
    private Long iuser;
    private Long ishop;
}
