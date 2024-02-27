package com.green.gogiro.owner.model;

import com.green.gogiro.entity.butcher.ButcherReviewEntity;
import com.green.gogiro.entity.shop.ShopReviewEntity;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.green.gogiro.common.Const.BUTCHER_CHECK_NUM;
import static com.green.gogiro.common.Const.SHOP_CHECK_NUM;
import static com.green.gogiro.entity.butcher.QButcherReviewEntity.butcherReviewEntity;
import static com.green.gogiro.entity.shop.QShopReviewEntity.shopReviewEntity;

@Slf4j
@RequiredArgsConstructor
public class ReviewQdslRepositoryImpl implements ReviewQdslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<OwnerReviewVo> selByReviewAll(long ishop, int checkShop, Pageable pageable) {

        if (checkShop == 0) {
            List<ShopReviewEntity> list = jpaQueryFactory
                    .selectFrom(shopReviewEntity)
                    .where(whereIshop(ishop, checkShop))
                    .orderBy(shopReviewEntity.ireview.desc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();
            return list.stream().map(item -> OwnerReviewVo.builder()
                    .checkShop(SHOP_CHECK_NUM)
                    .ireview(item.getIreview())
                    .review(item.getReview())
                    .iuser(item.getUserEntity().getIuser())
                    .pics(item.getShopReviewPicEntityList().stream().map(pic -> pic.getPic()).collect(Collectors.toList()))
                    .createdAt(item.getCreatedAt().toString())
                    .ishop(item.getIreview())
                    .exist(item.getComment() == null ? 0 : 1)
                    .updatedAt(item.getUpdatedAt() == null ? null : item.getUpdatedAt().toString())
                    .comment(item.getComment())
                    .star(item.getStar())
                    .build()).collect(Collectors.toList());
        }
        if (checkShop == 1) {
            List<ButcherReviewEntity> list = jpaQueryFactory
                    .selectFrom(butcherReviewEntity)
                    .where(whereIshop(ishop, checkShop))
                    .orderBy(butcherReviewEntity.ireview.desc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();

            return list.stream().map(item -> OwnerReviewVo.builder()
                    .ireview(item.getIreview())
                    .checkShop(BUTCHER_CHECK_NUM)
                    .iuser(item.getUserEntity().getIuser())
                    .pics(item.getButcherReviewPicEntityList().stream().map(pic -> pic.getPic()).collect(Collectors.toList()))
                    .review(item.getReview())
                    .comment(item.getComment())
                    .ishop(item.getButcherEntity().getIbutcher())
                    .createdAt(item.getCreatedAt().toString())
                    .exist(item.getComment() == null ? 0 : 1)
                    .updatedAt(item.getUpdatedAt().toString())
                    .star(item.getStar())
                    .build()).collect(Collectors.toList());
        }

        return null;
    }

    private BooleanExpression whereIshop(long ishop, int checkShop) {
        if (checkShop == 0) {
            return ishop == 0 ? null : shopReviewEntity.shopEntity.ishop.eq(ishop);
        }
        if (checkShop == 1) {
            return ishop == 0 ? null : butcherReviewEntity.butcherEntity.ibutcher.eq(ishop);
        }
        return null;
    }
}
