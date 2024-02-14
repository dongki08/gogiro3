package com.green.gogiro.reservation;

import com.green.gogiro.common.ResVo;
import com.green.gogiro.exception.AuthErrorCode;
import com.green.gogiro.exception.RestApiException;
import com.green.gogiro.reservation.model.*;
import com.green.gogiro.security.AuthenticationFacade;
import com.green.gogiro.shop.ShopMapper;
import com.green.gogiro.shop.model.ShopEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static com.green.gogiro.common.Const.REGEXP_PATTERN_SPACE_CHAR;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@Import({ReservationService.class})
class ReservationServiceTest {
    @MockBean
    private ReservationMapper mapper;
    @MockBean
    private AuthenticationFacade authenticationFacade;
    @MockBean
    private ShopMapper shopMapper;
    @Autowired
    private ReservationService service;

    @Test
    void postReservationTest() throws Exception {
        ReservationInsDto dto = new ReservationInsDto();
        try {
            service.postReservation(dto);
        } catch (Exception e) {
        }
        verify(shopMapper).selShopEntity(0);
    }

    @Test
    void postPickupTest() throws Exception {
        PickupInsDto dto = new PickupInsDto();
        dto.setDate("1000-01-01 00:00:00");
        List<PickupMenuDto> menus = new ArrayList<>();
        PickupMenuDto menu = PickupMenuDto.builder()
                .ibutMenu(1)
                .count(1)
                .build();
        menus.add(menu);
        dto.setMenus(menus);

        ResVo vo = service.postPickup(dto);
        verify(authenticationFacade).getLoginUserPk();
        verify(mapper).insPickup(any());
        verify(mapper).insPickupMenu(any());
        assertEquals(0, vo.getResult());

    }

    @Test
    void cancelReservationTest() throws Exception{
        CancelDto dto1= new CancelDto();
        dto1.setCheckShop(0);
        CancelDto dto2= new CancelDto();
        dto2.setCheckShop(1);
        ResVo vo1= service.cancelReservation(dto1);
        ResVo vo2= service.cancelReservation(dto2);
        verify(mapper).cancelReservation(dto1);
        verify(mapper).cancelPickup(dto2);
        assertEquals(1,vo1.getResult());
        assertEquals(1,vo2.getResult());
    }

    @Test
    void putReservationTest() throws Exception {
        ReservationUpdDto dto= new ReservationUpdDto();
        dto.setDate("1000-01-01 00:00:00");
        ResVo vo= service.putReservation(dto);
        verify(authenticationFacade).getLoginUserPk();
        verify(mapper).updReservation(any());
        assertEquals(1, vo.getResult());
    }
}
