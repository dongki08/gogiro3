package com.green.gogiro.owner.model;

import com.green.gogiro.entity.butcher.ButcherEntity;
import com.green.gogiro.entity.butcher.ButcherPicEntity;
import com.green.gogiro.entity.shop.ShopEntity;
import com.green.gogiro.entity.shop.ShopFacilitiesEntity;
import com.green.gogiro.entity.shop.ShopPicEntity;
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

import static com.green.gogiro.common.Const.BUTCHER_CHECK_NUM;
import static com.green.gogiro.common.Const.SHOP_CHECK_NUM;
import static com.green.gogiro.entity.butcher.QButcherEntity.butcherEntity;
import static com.green.gogiro.entity.butcher.QButcherPicEntity.butcherPicEntity;
import static com.green.gogiro.entity.shop.QShopEntity.shopEntity;
import static com.green.gogiro.entity.shop.QShopFacilitiesEntity.shopFacilitiesEntity;
import static com.green.gogiro.entity.shop.QFacilityEntity.facilityEntity;
import static com.green.gogiro.entity.shop.QShopPicEntity.shopPicEntity;

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
                    .checkShop(SHOP_CHECK_NUM)
                    .deposit(entity.getDeposit())
                    .imeat(entity.getShopCategoryEntity().getImeat())
                    .x(entity.getX())
                    .y(entity.getY())
                    .location(entity.getLocation())
                    .tel(entity.getTel())
                    .name(entity.getName())
                    .pics(selShopPics(ishop))
                    .ishop(entity.getIshop())
                    .number(entity.getNumber())
                    .open(entity.getOpen())
                    .facilities(selFacilityByShop(ishop))
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
                    .checkShop(BUTCHER_CHECK_NUM)
                    .x(entity.getX())
                    .y(entity.getY())
                    .location(entity.getLocation())
                    .name(entity.getName())
                    .number(entity.getNumber())
                    .ishop(entity.getIbutcher())
                    .tel(entity.getTel())
                    .pics(selButcherPics(ishop))
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

    public List<OwnerShopPicsProcVo> selShopPics(long ishop) {
        List<ShopPicEntity> list = jpaQueryFactory.selectFrom(shopPicEntity)
                .where(shopPicEntity.shopEntity.ishop.eq(ishop))
                .fetch();
        return list.stream().map(pic -> OwnerShopPicsProcVo.builder()
                .picsPk(pic.getIshopPics().intValue())
                .pic(pic.getPic())
                .build()).collect(Collectors.toList());
    }

    public List<OwnerShopPicsProcVo> selButcherPics(long ishop) {
        List<ButcherPicEntity> list =jpaQueryFactory.selectFrom(butcherPicEntity)
                .where(butcherPicEntity.butcherEntity.ibutcher.eq(ishop))
                .fetch();

        return list.stream().map(pic -> OwnerShopPicsProcVo.builder()
                .picsPk(pic.getIbutPics().intValue())
                .pic(pic.getPic())
                .build()).collect(Collectors.toList());
    }
}
