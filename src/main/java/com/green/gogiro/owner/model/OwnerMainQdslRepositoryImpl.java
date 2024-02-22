package com.green.gogiro.owner.model;

import com.green.gogiro.entity.shop.ShopEntity;
import com.green.gogiro.entity.shop.ShopFacilitiesEntity;
import com.green.gogiro.shop.model.ShopFacilityVo;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

import static com.green.gogiro.entity.shop.QShopEntity.shopEntity;
import static com.green.gogiro.entity.shop.QShopFacilitiesEntity.shopFacilitiesEntity;
import static com.green.gogiro.entity.shop.QFacilityEntity.facilityEntity;

@RequiredArgsConstructor
@Slf4j
public class OwnerMainQdslRepositoryImpl implements OwnerMainQdslRepository{
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public OwnerManagementVo selDetailShop(long ishop, int checkShop) {
        ShopEntity entity = jpaQueryFactory.selectFrom(shopEntity)
                .where(shopEntity.ishop.eq(ishop))
                .fetchOne();
        assert entity != null;
        return OwnerManagementVo.builder()
                .deposit(entity.getDeposit())
                .imeat(entity.getShopCategoryEntity().getImeat())
                .x(entity.getX())
                .y(entity.getY())
                .location(entity.getLocation())
                .tel(entity.getTel())
                .name(entity.getName())
                .pics(entity.getShopPicEntityList().stream().map(pic -> pic.getPic()).collect(Collectors.toList()))
                .ishop(entity.getIshop())
                .build();
    }

    @Override
    public List<FacilitiesVo> selFacilityByShop(long ishop) {
        List<ShopFacilitiesEntity> list = jpaQueryFactory.selectFrom(shopFacilitiesEntity)
                .where(shopFacilitiesEntity.shopEntity.ishop.eq(ishop))
                .join(facilityEntity.facilitiesEntityList)
                .fetch();
        return list.stream().map(item -> FacilitiesVo.builder()
                .ifacil(item.getFacilityEntity().getIfacil())
                .facility(item.getFacilityEntity().getFacility())
                .build()).collect(Collectors.toList());
    }
}
