package com.green.gogiro.owner.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DashBoardVo {
    private int totalBookmark;
    private int totalReservation;
    private int totalReview;
    private float starAvg;
    List<Integer> bookmarkCnt;
    List<Integer> reviewCnt;
    List<Integer> reservationCnt;
}
