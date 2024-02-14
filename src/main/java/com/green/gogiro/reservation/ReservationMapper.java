package com.green.gogiro.reservation;

import com.green.gogiro.reservation.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationMapper {
    int insReservation(ReservationInsDto dto);

    int insPickup(PickupInsDto dto);

    int insPickupMenu(PickupMenuDto dto);

    int cancelReservation(CancelDto dto);

    int cancelPickup(CancelDto dto);

    int updReservation(ReservationUpdDto dto);

    ReservationTestVo selReservationForTest(int ireser);

    int selPickupMenusForTest(int ipickup);

    CancelDto selReservationForCancelTest();

    Integer checkReservation(CancelDto dto);

    Integer checkReservationController(ReviewDto dto);

    int insReview(ReviewDto dto);

    int insReviewPics(ReviewPicsInsVo dto);

    int selReservationConfirmForTest(int ireser);

    CancelDto selPickupForCancelTest();

    int selPickupConfirmForTest(int ipickup);
}
