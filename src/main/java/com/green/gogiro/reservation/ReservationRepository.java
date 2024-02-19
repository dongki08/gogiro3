package com.green.gogiro.reservation;

import com.green.gogiro.entity.shop.ShopReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<ShopReservationEntity,Long> {
}
