package com.green.gogiro.owner.model;

import com.green.gogiro.entity.butcher.ButcherMenuEntity;
import com.green.gogiro.entity.butcher.QButcherMenuEntity;
import com.green.gogiro.entity.shop.QShopMenuEntity;
import com.green.gogiro.entity.shop.ShopMenuEntity;
import com.green.gogiro.entity.shop.ShopReviewEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

import static com.green.gogiro.entity.butcher.QButcherMenuEntity.butcherMenuEntity;
import static com.green.gogiro.entity.shop.QShopMenuEntity.shopMenuEntity;

@Slf4j
@RequiredArgsConstructor
public class OwnerMenuQdslRepositoryImpl implements OwnerMenuQdslRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<OwnerMenuVo> selMenu(long ishop, int checkShop) {
        if(checkShop == 0){
            List<ShopMenuEntity> list = jpaQueryFactory.selectFrom(shopMenuEntity)
                    .where(shopMenuEntity.shopEntity.ishop.eq(ishop))
                    .fetch();
            return list.stream().map(item -> OwnerMenuVo.builder()
                    .menu(item.getMenu())
                    .pic(item.getPic())
                    .price(item.getPrice())
                    .ishop(item.getShopEntity().getIshop())
                    .checkShop(checkShop)
                    .imenu(item.getImenu())
                    .build()).collect(Collectors.toList());
        }
        if(checkShop == 1){
            List<ButcherMenuEntity> list = jpaQueryFactory
                    .selectFrom(butcherMenuEntity)
                    .where(butcherMenuEntity.butcherEntity.ibutcher.eq(ishop))
                    .fetch();
            return list.stream().map(item -> OwnerMenuVo.builder()
                    .menu(item.getMenu())
                    .pic(item.getPic())
                    .price(item.getPrice())
                    .imenu(item.getIbutMenu())
                    .ishop(item.getButcherEntity().getIbutcher())
                    .checkShop(checkShop)
                    .build()).collect(Collectors.toList());
        }
        return null;
    }
}
