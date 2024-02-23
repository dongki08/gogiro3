package com.green.gogiro.owner.model;

import com.green.gogiro.entity.butcher.ButcherEntity;
import com.green.gogiro.entity.shop.ShopEntity;
import com.green.gogiro.entity.shop.ShopFacilitiesEntity;
import com.green.gogiro.exception.AuthErrorCode;
import com.green.gogiro.exception.RestApiException;
import com.green.gogiro.shop.model.ShopFacilityVo;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.green.gogiro.entity.butcher.QButcherEntity.butcherEntity;
import static com.green.gogiro.entity.shop.QShopEntity.shopEntity;
import static com.green.gogiro.entity.shop.QShopFacilitiesEntity.shopFacilitiesEntity;
import static com.green.gogiro.entity.shop.QFacilityEntity.facilityEntity;

@RequiredArgsConstructor
@Slf4j
public class OwnerMainQdslRepositoryImpl implements OwnerMainQdslRepository {
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public OwnerManagementVo selDetailShop(long ishop, int checkShop) {
        if (checkShop == 0) {
            ShopEntity entity = jpaQueryFactory.selectFrom(shopEntity)
                    .where(shopEntity.ishop.eq(ishop))
                    .fetchOne();
            if (entity == null) {
                throw new RestApiException(AuthErrorCode.VALID_SHOP);
            }
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
                    .number(entity.getNumber())
                    .open(entity.getOpen())
                    .build();
        }
        if (checkShop == 1) {
            long imeat = 0;
            ButcherEntity entity = jpaQueryFactory.selectFrom(butcherEntity)
                    .where(butcherEntity.ibutcher.eq(ishop))
                    .fetchOne();
            if (entity == null) {
                throw new RestApiException(AuthErrorCode.VALID_SHOP);
            }
            return OwnerManagementVo.builder()
                    .x(entity.getX())
                    .y(entity.getY())
                    .location(entity.getLocation())
                    .name(entity.getName())
                    .number(entity.getNumber())
                    .ishop(entity.getIbutcher())
                    .tel(entity.getTel())
                    .pics(entity.getButcherPicEntityList().stream().map(pic -> pic.getPic()).collect(Collectors.toList()))
                    .open(entity.getOpen())
                    .imeat(imeat)
                    .build();
        }
        return null;
    }

    @Override
    public List<FacilitiesVo> selFacilityByShop(long ishop) {
        List<ShopFacilitiesEntity> list = jpaQueryFactory.selectFrom(shopFacilitiesEntity)
                .where(shopFacilitiesEntity.shopEntity.ishop.eq(ishop))
                .fetch();
        return list.stream().map(item -> FacilitiesVo.builder()
                .ifacil(item.getFacilityEntity().getIfacil())
                .facility(item.getFacilityEntity().getFacility())
                .build()).collect(Collectors.toList());
    }
}
