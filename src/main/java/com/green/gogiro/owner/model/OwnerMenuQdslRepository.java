package com.green.gogiro.owner.model;

import java.util.List;

public interface OwnerMenuQdslRepository  {
    List<OwnerMenuVo> selMenu(long ishop,int checkShop);
}
