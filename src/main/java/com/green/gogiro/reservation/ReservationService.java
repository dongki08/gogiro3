package com.green.gogiro.reservation;

import static com.green.gogiro.common.Const.*;
import static com.green.gogiro.exception.AuthErrorCode.*;
import static com.green.gogiro.exception.ReservationErrorCode.CANT_CANCEL;
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
import com.green.gogiro.shop.model.ShopModel;
import com.green.gogiro.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDateTime;
import java.util.List;

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

    //Mybatis 1.예약 등록
    @Transactional
    public ResVo postReservation1(ReservationInsDto dto) {
        ShopModel entity = shopMapper.selShopEntity(dto.getIshop());
        if (entity == null) {
            throw new RestApiException(VALID_SHOP);
        }
        dto.setIuser((int)authenticationFacade.getLoginUserPk());
        mapper.insReservation(dto);
        return new ResVo(dto.getIreser());
    }
    //JPA 1.예약 등록
    @Transactional
    public ResVo postReservation2(ReservationInsDto dto){
        ShopEntity shopEntity=shopRepository.getReferenceById((long)dto.getIshop());
        if(shopEntity.getIshop().intValue()==0){
            throw new RestApiException(VALID_SHOP);
        }
        ReservationEntity entity= new ReservationEntity();
        entity.setUserEntity(userRepository.getReferenceById(authenticationFacade.getLoginUserPk()));
        entity.setShopEntity(shopEntity);
        entity.setDate(LocalDateTime.parse(dto.getDate()));
        entity.setRequest(dto.getRequest());
        entity.setHeadCount(dto.getHeadCount());
        repository.save(entity);
        return new ResVo(entity.getIreser().intValue());
    }
    //Mybatis 2.픽업 등록
    @Transactional
    public ResVo postPickup1(PickupInsDto dto) {
        List<Integer> menuList = butMapper.selButcherMenu(dto.getIbutcher());
        List<Integer> list= menuList.stream().filter(item->{
            for(PickupMenuDto menu: dto.getMenus()){
                if(item==menu.getIbutMenu()){
                    return true;
                }
            }
            return false;
        }).toList();
        if(list.size()!=dto.getMenus().size()){
            throw new RestApiException(AuthErrorCode.INVALID_MENU_OR_COUNT);
        }
        dto.setIuser((int)authenticationFacade.getLoginUserPk());
        mapper.insPickup(dto);
        for (PickupMenuDto m : dto.getMenus()) {
            PickupMenuDto menu = PickupMenuDto.builder()
                    .ipickup(dto.getIpickup())
                    .ibutMenu(m.getIbutMenu())
                    .count(m.getCount())
                    .build();
            mapper.insPickupMenu(menu);
        }
        return new ResVo(dto.getIpickup());
    }
    //JPA 2.픽업 등록
    @Transactional
    public ResVo postPickup2(PickupInsDto dto){
        ButcherEntity butcherEntity=butcherRepository.getReferenceById((long)dto.getIbutcher());
        List<Integer> menuList= butcherMenuRepository.findByButcherEntity(butcherEntity)
                .stream().map(item->item.getIbutMenu().intValue()).toList();
        List<Integer> list= menuList.stream().filter(item->{
                for(PickupMenuDto menu: dto.getMenus()){
                    if(item==menu.getIbutMenu()){return true;}
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
        entity.setDate(LocalDateTime.parse(dto.getDate()));
        entity.setRequest(dto.getRequest());
        pickupRepository.save(entity);
        entity.getPickupMenuEntityList().addAll(
                dto.getMenus().stream().map(item->
                        PickupMenuEntity.builder()
                                .pickupEntity(entity)
                                .butcherMenuEntity(butcherMenuRepository.getReferenceById(
                                        (long)item.getIbutMenu()
                                        )
                                )
                                .count(item.getCount())
                                .build()).toList()
        );
        return new ResVo(entity.getIpickup().intValue());
    }
    //Mybatis 3.예약 취소
    @Transactional
    public ResVo cancelReservation1(CancelDto dto) {
        dto.setIuser((int)authenticationFacade.getLoginUserPk());
        Integer checkReservation = mapper.checkReservation(dto);
        if (checkReservation == null) {
            throw new RestApiException(AuthErrorCode.INVALID_RESERVATION);
        }
        if (dto.isReservation()) {
            mapper.cancelReservation(dto);
        } else {
            mapper.cancelPickup(dto);
        }
        return new ResVo(SUCCESS);
    }
    //JPA 3.예약 취소
    @Transactional
    public ResVo cancelReservation2(CancelDto dto){
        ReservationEntity entity= reservationRepository.getReferenceById((long)dto.getIreser());
        if(entity.getUserEntity().getIuser()!=authenticationFacade.getLoginUserPk()){
            throw new RestApiException(CANT_CANCEL);
        }
        if(dto.isReservation()){
            //예약 취소
            reservationRepository.getReferenceById((long)dto.getIreser()).setConfirm(1);
        }else{
            //픽업 취소
            pickupRepository.getReferenceById((long)dto.getIreser()).setConfirm(1);
        }
        return new ResVo(SUCCESS);
    }
    //Mybatis 4.예약 변경
    @Transactional
    public ResVo putReservation1(ReservationUpdDto dto) {
        if((LocalDateTime.now()).isAfter(LocalDateTime.parse(dto.getDate()))){
            throw new RestApiException(AuthErrorCode.NOT_DATE);
        }
        dto.setIuser((int)authenticationFacade.getLoginUserPk());
        mapper.updReservation(dto);
        return new ResVo(SUCCESS);
    }
    //JPA 4.예약 변경
    @Transactional
    public ResVo putReservation2(ReservationUpdDto dto){
        if((LocalDateTime.now()).isAfter(LocalDateTime.parse(dto.getDate()))){
            throw new RestApiException(AuthErrorCode.NOT_DATE);
        }
        if(dto.isReservation()){
            ReservationEntity reservation=reservationRepository.getReferenceById((long)dto.getIreser());
            if(reservation.getUserEntity().getIuser()!= authenticationFacade.getLoginUserPk()){
                throw new RestApiException((CommonErrorCode.UNAUTHORIZED));
            }
            reservation.setDate(LocalDateTime.parse(dto.getDate()));
            reservation.setRequest(dto.getRequest());
            reservation.setHeadCount(dto.getHeadCount());
        }else{
            PickupEntity pickup=pickupRepository.getReferenceById((long)dto.getIreser());
            if(pickup.getUserEntity().getIuser()!= authenticationFacade.getLoginUserPk()){
                throw new RestApiException((CommonErrorCode.UNAUTHORIZED));
            }
            pickup.setDate(LocalDateTime.parse(dto.getDate()));
            pickup.setRequest(dto.getRequest());
        }
        return new ResVo(SUCCESS);
    }
    //Mybatis 5.후기 작성
    @Transactional
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
    }
    //JPA 5.후기 작성
    @Transactional
    public ReviewPicsInsVo postReview2(ReviewDto dto) {
        ReviewPicsInsVo vo = new ReviewPicsInsVo();
        vo.setCheckShop(dto.getCheckShop());
        long iuser= authenticationFacade.getLoginUserPk();
        String target = (dto.getCheckShop()==0?"/shop/":"/butcher/")
                        + dto.getIshop()
                        + "/review/" + dto.getIreview()
                        + "/";
        if(dto.isReservation()){
            ReservationEntity reservation=reservationRepository.getReferenceById((long)dto.getIreser());
            if(iuser!=reservation.getUserEntity().getIuser()){
                throw new RestApiException((CommonErrorCode.UNAUTHORIZED));
            }
            ShopReviewEntity entity= new ShopReviewEntity();
            entity.setShopEntity(shopRepository.getReferenceById((long)dto.getIshop()));
            entity.setUserEntity(userRepository.getReferenceById(iuser));
            entity.setStar(dto.getStar());
            entity.setReview(dto.getReview());
            shopReviewRepository.save(entity);
            vo.setIreview(dto.getIreview());
            for(MultipartFile file : dto.getFiles()){
                String saveFileNm = myFileUtils.transferTo(file, target);
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
            PickupEntity pickup=pickupRepository.getReferenceById((long)dto.getIreser());
            if(iuser!=pickup.getUserEntity().getIuser()){
                throw new RestApiException((CommonErrorCode.UNAUTHORIZED));
            }
            ButcherReviewEntity entity= new ButcherReviewEntity();
            entity.setButcherEntity(butcherRepository.getReferenceById((long)dto.getIshop()));
            entity.setUserEntity(userRepository.getReferenceById(iuser));
            entity.setStar(dto.getStar());
            entity.setReview(dto.getReview());
            butcherReviewRepository.save(entity);
            vo.setIreview(dto.getIreview());
            for(MultipartFile file : dto.getFiles()){
                String saveFileNm = myFileUtils.transferTo(file, target);
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
}

