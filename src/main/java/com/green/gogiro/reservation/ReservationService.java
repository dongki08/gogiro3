package com.green.gogiro.reservation;

import static com.green.gogiro.common.Const.*;
import static com.green.gogiro.exception.AuthErrorCode.*;
import static com.green.gogiro.exception.ReservationErrorCode.*;
import static java.lang.Math.abs;

import com.green.gogiro.butchershop.ButcherShopMapper;
import com.green.gogiro.common.MyFileUtils;
import com.green.gogiro.common.ResVo;
import com.green.gogiro.entity.butcher.*;
import com.green.gogiro.entity.butcher.repository.ButcherMenuRepository;
import com.green.gogiro.entity.butcher.repository.ButcherRepository;
import com.green.gogiro.entity.butcher.repository.ButcherReviewRepository;
import com.green.gogiro.entity.butcher.repository.PickupRepository;
import com.green.gogiro.entity.shop.ShopEntity;
import com.green.gogiro.entity.shop.ReservationEntity;
import com.green.gogiro.entity.shop.ShopReviewEntity;
import com.green.gogiro.entity.shop.ShopReviewPicEntity;
import com.green.gogiro.entity.shop.repository.ReservationRepository;
import com.green.gogiro.entity.shop.repository.ShopReviewRepository;
import com.green.gogiro.exception.AuthErrorCode;
import com.green.gogiro.exception.CommonErrorCode;
import com.green.gogiro.exception.RestApiException;
import com.green.gogiro.reservation.model.*;
import com.green.gogiro.security.AuthenticationFacade;
import com.green.gogiro.shop.ShopMapper;
import com.green.gogiro.entity.shop.repository.ShopRepository;
import com.green.gogiro.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
@Service
public class ReservationService {
    private final ReservationMapper mapper;
    private final ShopMapper shopMapper;
    private final ButcherShopMapper butMapper;
    private final MyFileUtils myFileUtils;
    private final AuthenticationFacade authenticationFacade;
    private final ReservationRepository repository;
    private final UserRepository userRepository;
    private final ShopRepository shopRepository;
    private final ButcherRepository butcherRepository;
    private final ButcherMenuRepository butcherMenuRepository;
    private final PickupRepository pickupRepository;
    private final ReservationRepository reservationRepository;
    private final ShopReviewRepository shopReviewRepository;
    private final ButcherReviewRepository butcherReviewRepository;

//    //Mybatis 1.예약 등록
//    @Transactional
//    public ReservationVo postReservation1(ReservationInsDto dto) {
//        ShopModel entity = shopMapper.selShopEntity(dto.getIshop());
//        if (entity == null) {
//            throw new RestApiException(VALID_SHOP);
//        }
//        dto.setIuser((int)authenticationFacade.getLoginUserPk());
//        mapper.insReservation(dto);
//        ReservationVo vo= new ReservationVo();
//
//        vo.setAmount(mapper.reservationAmount(dto.getIreser()));
//        vo.setIreser(dto.getIreser());
//        return vo;
//    }
    //JPA 1.예약 등록
    @Transactional
    public ReservationVo postReservation2(ReservationInsDto dto){
        ShopEntity shopEntity=shopRepository.getReferenceById((long)dto.getIshop());
        if(shopEntity.getIshop().intValue()==0){
            throw new RestApiException(VALID_SHOP);
        }
        ReservationEntity entity= new ReservationEntity();
        entity.setUserEntity(userRepository.getReferenceById(authenticationFacade.getLoginUserPk()));
        entity.setShopEntity(shopEntity);
        entity.setDate(dto.getLocalDateTime());
        entity.setRequest(dto.getRequest());
        entity.setHeadCount(dto.getHeadCount());
        repository.save(entity);
        ReservationVo vo= new ReservationVo();
        vo.setAmount(shopEntity.getDeposit());
        vo.setPk(entity.getIreser().intValue());
        return vo;
    }
//    //Mybatis 2.픽업 등록
//    @Transactional
//    public ResVo postPickup1(PickupInsDto dto) {
//        List<Integer> menuList = butMapper.selButcherMenu(dto.getIbutcher());
//        List<Integer> list= menuList.stream().filter(item->{
//            for(PickupMenuDto menu: dto.getMenus()){
//                if(item==menu.getIbutMenu()){
//                    return true;
//                }
//            }
//            return false;
//        }).toList();
//        if(list.size()!=dto.getMenus().size()){
//            throw new RestApiException(AuthErrorCode.INVALID_MENU_OR_COUNT);
//        }
//        dto.setIuser((int)authenticationFacade.getLoginUserPk());
//        mapper.insPickup(dto);
//        for (PickupMenuDto m : dto.getMenus()) {
//            PickupMenuDto menu = PickupMenuDto.builder()
//                    .ipickup(dto.getIpickup())
//                    .ibutMenu(m.getIbutMenu())
//                    .count(m.getCount())
//                    .build();
//            mapper.insPickupMenu(menu);
//        }
//        return new ResVo(dto.getIpickup());
//    }
    //JPA 2.픽업 등록
    @Transactional
    public ReservationVo postPickup2(PickupInsDto dto){
        ButcherEntity butcherEntity=butcherRepository.getReferenceById((long)dto.getIbutcher());
        List<ButcherMenuEntity> menuList= butcherMenuRepository.findByButcherEntity(butcherEntity);
        AtomicInteger amount= new AtomicInteger();
        List<ButcherMenuEntity> list= menuList.stream().filter(item->{
                for(PickupMenuDto menu: dto.getMenus()){
                    if(item.getIbutMenu()==menu.getIbutMenu()){
                        amount.addAndGet(item.getPrice() * menu.getCount());
                        return true;
                    }
                }
                return false;
            }
        ).toList();
        if(list.size()!=dto.getMenus().size()){
            throw new RestApiException(AuthErrorCode.INVALID_MENU_OR_COUNT);
        }
        PickupEntity entity= new PickupEntity();
        entity.setUserEntity(userRepository.getReferenceById(authenticationFacade.getLoginUserPk()));
        entity.setButcherEntity(butcherEntity);
        entity.setDate(dto.getLocalDateTime());
        entity.setRequest(dto.getRequest());
        entity.setTotal(amount.intValue());
        pickupRepository.save(entity);
        entity.getPickupMenuEntityList()
              .addAll(dto.getMenus().stream().map(item->{
                  ButcherMenuEntity menuEntity=butcherMenuRepository.getReferenceById((long)item.getIbutMenu());
                  PickupMenuIds ids=new PickupMenuIds();
                  ids.setIbutMenu(menuEntity.getIbutMenu());
                  ids.setIpickup(entity.getIpickup());
                  return PickupMenuEntity.builder()
                                         .pickupMenuIds(ids)
                                         .pickupEntity(entity)
                                         .butcherMenuEntity(menuEntity)
                                         .count(item.getCount())
                                         .build();
                  }).toList()
              );
        ReservationVo vo= new ReservationVo();
        vo.setAmount(amount.intValue());
        vo.setPk(entity.getIpickup().intValue());
        return vo;
    }
    //Mybatis 3.예약 취소
    /*@Transactional
    public ResVo cancelReservation1(CancelDto dto) {
        dto.setIuser((int)authenticationFacade.getLoginUserPk());
        Integer checkReservation = mapper.checkReservation(dto);
        if (checkReservation == null) {
            throw new RestApiException(INVALID_RESERVATION);
        }
        if (dto.isReservation()) {
            mapper.cancelReservation(dto);
        } else {
            mapper.cancelPickup(dto);
        }
        return new ResVo(SUCCESS);
    }*/
    //JPA 3.예약 취소
    @Transactional
    public ResVo cancelReservation2(CancelDto dto){
        if(dto.isReservation()){
            //예약 취소
            Optional<ReservationEntity> optEntity= reservationRepository.findById((long)dto.getIreser());
            ReservationEntity reservation= optEntity.orElseThrow(()->new RestApiException(INVALID_RESERVATION));
            LocalDate dayDate1 = LocalDateTime.now().toLocalDate();
            LocalDate dayDate2 = reservation.getDate().toLocalDate();
            Period diff= Period.between(dayDate1, dayDate2);
            if(reservation.getUserEntity().getIuser()!=authenticationFacade.getLoginUserPk()){
                throw new RestApiException(CANT_CANCEL);
            } else if(abs(diff.getYears())==0&&abs(diff.getMonths())==0&&abs(diff.getDays())==0){
                throw new RestApiException(CANT_UPDATE);
            } else if(reservation.getDate().isAfter(LocalDateTime.now())){
                throw new RestApiException(PASSED_BY_DATE);
            }
            reservation.setConfirm(1);
        }else{
            //픽업 취소
            Optional<PickupEntity> optPickup= pickupRepository.findById((long)dto.getIreser());
            PickupEntity pickup= optPickup.orElseThrow(()->new RestApiException(INVALID_RESERVATION));
            LocalDate dayDate1 = LocalDateTime.now().toLocalDate();
            LocalDate dayDate2 = pickup.getDate().toLocalDate();
            Period diff= Period.between(dayDate1, dayDate2);
            if(pickup.getUserEntity().getIuser()!=authenticationFacade.getLoginUserPk()){
                throw new RestApiException(CANT_CANCEL);
            } else if(abs(diff.getYears())==0&&abs(diff.getMonths())==0&&abs(diff.getDays())==0){
                throw new RestApiException(CANT_UPDATE);
            } else if(pickup.getDate().isAfter(LocalDateTime.now())){
                throw new RestApiException(PASSED_BY_DATE);
            }
            pickup.setConfirm(1);
        }
        return new ResVo(SUCCESS);
    }
   /* //Mybatis 4.예약 변경
    @Transactional
    public ResVo putReservation1(ReservationUpdDto dto) {
        if((LocalDateTime.now()).isAfter(LocalDateTime.parse(dto.getDate()))){
            throw new RestApiException(AuthErrorCode.NOT_DATE);
        }
        dto.setIuser((int)authenticationFacade.getLoginUserPk());
        mapper.updReservation(dto);
        return new ResVo(SUCCESS);
    }*/
    //JPA 4.예약 변경
    @Transactional
    public ResVo putReservation2(ReservationUpdDto dto){
        if(dto.getLocalDateTime().isBefore(LocalDateTime.now())){
            throw new RestApiException(PASSED_BY_DATE);
        } else if(dto.isInvalidDate()){
            throw new RestApiException(INVALID_DATE);
        }
        if(dto.isReservation()){
            Optional<ReservationEntity> optEntity= reservationRepository.findById((long)dto.getIreser());
            ReservationEntity reservation= optEntity.orElseThrow(()->new RestApiException(INVALID_RESERVATION));
            if(reservation.getUserEntity().getIuser()!= authenticationFacade.getLoginUserPk()){
                throw new RestApiException((CommonErrorCode.UNAUTHORIZED));
            }
            reservation.setDate(dto.getLocalDateTime());
            reservation.setRequest(dto.getRequest());
            reservation.setHeadCount(dto.getHeadCount());
        }else{
            Optional<PickupEntity> optPickup= pickupRepository.findById((long)dto.getIreser());
            PickupEntity pickup= optPickup.orElseThrow(()->new RestApiException(INVALID_RESERVATION));
            if(pickup.getUserEntity().getIuser()!= authenticationFacade.getLoginUserPk()){
                throw new RestApiException((CommonErrorCode.UNAUTHORIZED));
            }
            pickup.setDate(dto.getLocalDateTime());
            pickup.setRequest(dto.getRequest());
        }
        return new ResVo(SUCCESS);
    }
    //Mybatis 5.후기 작성
    /*@Transactional
    public ReviewPicsInsVo postReview1(ReviewDto dto) {
        ReviewPicsInsVo vo = new ReviewPicsInsVo();
        vo.setCheckShop(dto.getCheckShop());
        dto.setIuser((int)authenticationFacade.getLoginUserPk());

        Integer check = mapper.checkReservationController(dto);
        if(check != null){
            if(check == dto.getIuser()){
                mapper.insReview(dto);
                String target = (dto.getCheckShop() == 0 ? "/shop/" : "/butcher/")
                                + dto.getIshop()
                                + "/review/" + dto.getIreview() + "/";
                vo.setIreview(dto.getIreview());
                for (MultipartFile file : dto.getFiles()) {
                    String saveFileNm = myFileUtils.transferTo(file, target);
                    vo.getPics().add(saveFileNm);
                }
                mapper.insReviewPics(vo);
            }
            return vo;
        }
        //해당 유저의 예약 혹은 픽업이 아닌 경우
        throw new RestApiException(INVALID_RESERVATION);
    }*/
    //JPA 5.후기 작성
    @Transactional
    public ReviewPicsInsVo postReview2(ReviewDto dto) {
        ReviewPicsInsVo vo = new ReviewPicsInsVo();
        vo.setCheckShop(dto.getCheckShop());
        long iuser= authenticationFacade.getLoginUserPk();
        String target = (dto.getCheckShop()==0?"/shop/":"/butcher/") + dto.getIshop() +"/review/";
        if(dto.isReservation()){
            Optional<ReservationEntity> optReservation= reservationRepository.findById((long)dto.getIreser());
            ReservationEntity reservation= optReservation.orElseThrow(()->new RestApiException(INVALID_RESERVATION));
            if(iuser!=reservation.getUserEntity().getIuser()){
                throw new RestApiException(CommonErrorCode.UNAUTHORIZED);
            } else if(reservation.getShopEntity().getIshop()!=dto.getIshop()){
                throw new RestApiException(INVALID_SHOP);
            }
            ShopReviewEntity entity= new ShopReviewEntity();
            entity.setShopEntity(shopRepository.getReferenceById((long)dto.getIshop()));
            entity.setUserEntity(userRepository.getReferenceById(iuser));
            entity.setStar(dto.getStar());
            entity.setReview(dto.getReview());
            shopReviewRepository.save(entity);
            vo.setIreview(entity.getIreview().intValue());
            for(MultipartFile file : dto.getFiles()){
                String saveFileNm = myFileUtils.transferTo(file, target+entity.getIreview()+"/");
                vo.getPics().add(saveFileNm);
            }
            entity.getShopReviewPicEntityList()
                  .addAll(vo.getPics().stream()
                                      .map(item -> ShopReviewPicEntity.builder()
                                                                      .shopReviewEntity(entity)
                                                                      .pic(item)
                                                                      .build())
                  .toList());
        }else{
            Optional<PickupEntity> optPickup= pickupRepository.findById((long)dto.getIreser());
            PickupEntity pickup= optPickup.orElseThrow(()->new RestApiException(INVALID_RESERVATION));
            if(iuser!=pickup.getUserEntity().getIuser()){
                throw new RestApiException((CommonErrorCode.UNAUTHORIZED));
            }else if(pickup.getButcherEntity().getIbutcher()!=dto.getIshop()){
                throw new RestApiException(INVALID_SHOP);
            }
            ButcherReviewEntity entity= new ButcherReviewEntity();
            entity.setButcherEntity(butcherRepository.getReferenceById((long)dto.getIshop()));
            entity.setUserEntity(userRepository.getReferenceById(iuser));
            entity.setStar(dto.getStar());
            entity.setReview(dto.getReview());
            butcherReviewRepository.save(entity);
            vo.setIreview(entity.getIreview().intValue());
            for(MultipartFile file : dto.getFiles()){
                String saveFileNm = myFileUtils.transferTo(file, target+entity.getIreview()+"/");
                vo.getPics().add(saveFileNm);
            }
            entity.getButcherReviewPicEntityList()
                  .addAll(vo.getPics().stream()
                                      .map(item -> ButcherReviewPicEntity.builder()
                                                                         .butcherReviewEntity(entity)
                                                                         .pic(item)
                                                                         .build())
                                      .toList());
        }
        return vo;
    }
    public ResVo confirmReservation(ConfirmDto dto){
        if(authenticationFacade.getLoginOwnerCheckShop()!=dto.getCheckShop()){
            throw new RestApiException(CommonErrorCode.INVALID_PARAMETER);
        }
        if(dto.isReservation()){
            Optional<ReservationEntity> optional=reservationRepository.findById((long)dto.getIreser());
            ReservationEntity reservation=optional.orElseThrow(()->new RestApiException(INVALID_RESERVATION));
            if(reservation.getIreser()!=dto.getIreser()){throw new RestApiException(INVALID_RESERVATION);}
            if(reservation.getConfirm()==1){throw new RestApiException(ALREADY_CANCELED);}
            reservation.setConfirm(2);
        }else{
            Optional<PickupEntity> optional=pickupRepository.findById((long)dto.getIreser());
            PickupEntity pickup=optional.orElseThrow(()->new RestApiException(INVALID_RESERVATION));
            if(pickup.getIpickup()!=dto.getIreser()){throw new RestApiException(INVALID_RESERVATION);}
            if(pickup.getConfirm()==1){throw new RestApiException(ALREADY_CANCELED);}
            pickup.setConfirm(2);
        }
        return new ResVo(SUCCESS);
    }
    //결제 확인(금액 비교)
    @Transactional
    public boolean confirmPayment(PaymentDto dto){
        boolean check;
        if(dto.isReservation()){
            Optional<ReservationEntity> optional=reservationRepository.findById((long)dto.getIreser());
            ReservationEntity reservation=optional.orElseThrow(()->new RestApiException(INVALID_RESERVATION));
            check=(reservation.getShopEntity().getDeposit()!=dto.getAmount());
        } else{
            Optional<PickupEntity> optional=pickupRepository.findById((long)dto.getIreser());
            PickupEntity pickup=optional.orElseThrow(()->new RestApiException(INVALID_RESERVATION));
            check=(pickup.getTotal()!=dto.getAmount());
        }
        return check;
    }
}

