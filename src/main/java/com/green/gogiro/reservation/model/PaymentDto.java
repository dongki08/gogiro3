package com.green.gogiro.reservation.model;

import lombok.Data;

@Data
public class PaymentDto {
    private String orderId;//주문ID
    private int amount;//최종결제금액
    private String paymentKey;//결제ID
}
