package com.green.gogiro.community;

import static com.green.gogiro.common.Const.*;
import static com.green.gogiro.exception.AuthErrorCode.*;


import com.green.gogiro.common.Const;
import com.green.gogiro.common.MyFileUtils;
import com.green.gogiro.common.ResVo;
import com.green.gogiro.community.model.*;
import com.green.gogiro.entity.UserEntity;
import com.green.gogiro.entity.community.*;
import com.green.gogiro.entity.community.repository.*;
import com.green.gogiro.entity.shop.repository.ReservationRepository;
import com.green.gogiro.exception.AuthErrorCode;
import com.green.gogiro.exception.RestApiException;
import com.green.gogiro.security.AuthenticationFacade;
import com.green.gogiro.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class CommunityService {
    private final CommunityMapper mapper;
    private final MyFileUtils myFileUtils;
    private final AuthenticationFacade authenticationFacade;
    private final UserRepository userRepository;
    private final CommunityRepository communityRepository;
    private final CommunityFavRepository communityFavRepository;
    private final CommunityCountRepository communityCountRepository;
    private final CommunityReportRepository communityReportRepository;
    private final CommunityCommentCountRepository communityCommentCountRepository;
    private final CommunityCommentRepository communityCommentRepository;
    private final ReservationRepository reservationRepository;


    //커뮤니티 게시글 등록
    @Transactional
    public CommunityPicsInsVo insCommunity(List<MultipartFile> pics, CommunityInsDto dto) {

        CommunityEntity communityentity = new CommunityEntity();
        communityentity.setUserEntity(userRepository.getReferenceById(authenticationFacade.getLoginUserPk()));

        if(authenticationFacade.getLoginUserRole().equals("ADMIN")) {
            communityentity.setAnnounce(1);
        } else {
            communityentity.setAnnounce(0);
        }
        communityentity.setIboard(dto.getIboard());
        communityentity.setTitle(dto.getTitle());
        communityentity.setContents(dto.getContents());
        communityRepository.save(communityentity);


        String target = "/community/" + communityentity.getIboard();
        CommunityPicsInsVo vo = new CommunityPicsInsVo();
        vo.setIboard(communityentity.getIboard().intValue());
        if(pics != null) {
            for(MultipartFile file : pics) {
                String saveFileNm = myFileUtils.transferTo(file, target);
                vo.getPics().add(saveFileNm);
            }
        }
        List<CommunityPicsEntity> communityPicsEntityList = vo.getPics()
                .stream()
                .map(item -> CommunityPicsEntity.builder()
                        .communityEntity(communityentity)
                        .pic(item)
                        .build())
                .collect(Collectors.toList());
        communityentity.getCommunityPicsEntityList().addAll(communityPicsEntityList);

        return vo;

    }
//    public CommunityPicsInsVo insCommunity(CommunityInsDto dto) {
//
//        dto.setIuser((int)authenticationFacade.getLoginUserPk());
//
////        //제목을 입력하지 않는 경우
////        if(dto.getTitle() == null || Pattern.matches(Const.REGEXP_PATTERN_SPACE_CHAR, dto.getTitle())) {
////            throw new RestApiException(AuthErrorCode.NOT_COMMUNITY_TITLE);
////        }
////        //내용을 입력하지 않는 경우
////        if(dto.getContents() == null || Pattern.matches(Const.REGEXP_PATTERN_SPACE_CHAR, dto.getContents())) {
////            throw new RestApiException(AuthErrorCode.NOT_CONTENT);
////        }
//        mapper.insCommunity(dto);
//        if (dto.getFiles() != null) {
//            String target = "/community/" + dto.getIboard();
//            for (MultipartFile file : dto.getFiles()) {
//                String saveFileNm = myFileUtils.transferTo(file, target);
//                dto.getPics().add(saveFileNm);
//            }
//            mapper.insCommunityPics(dto);
//        }
//
//        //auto_increment 0 값일때
//        if (dto.getIboard() == 0) {
//            throw new RestApiException(AuthErrorCode.NOT_COMMUNITY);
//        }
//        CommunityPicsInsVo vo = CommunityPicsInsVo.builder()
//                .iboard(dto.getIboard())
//                .pics(dto.getPics())
//                .build();
//        return vo;
//    }


//    public CommunityPicsInsVo updCommunity(CommunityUpdDto dto) {
//        Optional<CommunityEntity> optComu = communityRepository.findByIboard((long)dto.getIboard());
//        CommunityEntity communityEntity = optComu.orElseThrow(() -> new RestApiException(NOT_COMMUNITY_CHECK));
//
//        if(communityEntity.getUserEntity().getIuser() != authenticationFacade.getLoginUserPk()) {
//            throw new RestApiException(NOT_COMMUNITY_ENTITY);
//        }
//        int boardSize = communityEntity.getCommunityPicsEntityList().size();
//        int fileSize = dto.getFiles() != null ? dto.getFiles().size() : 0;
//        int delSize = dto.getIcommuPics() != null ? dto.getIcommuPics().size() : 0;
//        int totalSize = boardSize + fileSize - delSize;
//
//    }

    //커뮤니티 게시글 수정
    @Transactional
    public CommunityPicsInsVo updCommunity(CommunityUpdDto dto) {
        //Integer check = mapper.checkCommunity(dto.getIboard());
        CommunityModel model = mapper.entityCommunity(dto.getIboard());
        List<CommunityBySelPicsDto> bDto = mapper.selByCommunityPics(dto.getIboard());
        List<Integer> picPk = mapper.selByCommunityPicsPk(dto.getIboard());

        int boardSize = bDto != null ? bDto.size() : 0;
        int fileSize = dto.getFiles() != null ? dto.getFiles().size() : 0;
        int delSize = dto.getIcommuPics() != null ? dto.getIcommuPics().size() : 0;
        int totalSize = boardSize + fileSize - delSize;

        //본인 게시글 확인
        if(model.getIuser() != authenticationFacade.getLoginUserPk()) {
            throw new RestApiException(NOT_COMMUNITY_ENTITY);
        }
        //사진pk 여부
        if(dto.getIcommuPics().size() != dto.getIcommuPics()
                .stream()
                .filter(item-> { for(Integer pk: picPk) {
                    if(item == pk) { return true; }
                }
                    return false;
                }
        ).toList().size()) {
            throw new RestApiException(NOT_COMMUNITY_PICSPKENTITY);
        }


        if (model == null) {
            throw new RestApiException(AuthErrorCode.NOT_COMMUNITY_CHECK);
        } else if (totalSize > 5) {
            throw new RestApiException(SIZE_PHOTO);
        }
        dto.setIuser((int)authenticationFacade.getLoginUserPk());
        mapper.updCommunity(dto);
        String target = "/community/" + dto.getIboard();
        if (dto.getIcommuPics() != null && !dto.getIcommuPics().isEmpty()) {
            List<CommunityBySelPicsDto> cDto = mapper.selCommunityPics(dto.getIcommuPics());
            for (CommunityBySelPicsDto pics : cDto) {
                log.info("pics: {}", pics.getPic());
                myFileUtils.delFolderTrigger2(target + "/" + pics.getPic());
            }
            mapper.delCommunityPic(dto.getIcommuPics());

        }
        if (dto.getFiles() != null) {
            for (MultipartFile file : dto.getFiles()) {
                String saveFileNm = myFileUtils.transferTo(file, target);
                dto.getPics().add(saveFileNm);
            }
            mapper.insCommunityPics(dto);
        }

        CommunityPicsInsVo vo = CommunityPicsInsVo.builder()
                .iboard(dto.getIboard())
                .pics(dto.getPics())
                .build();
        return vo;
    }

    //커뮤니티 게시글 리스트
    public List<CommunitySelVo> selCommunity(CommunitySelDto dto) {
        //검색창 공백
        if (Pattern.matches(Const.REGEXP_PATTERN_SPACE_CHAR_TYPE_2, dto.getSearch())) {
            throw new RestApiException(NOT_CONTENT);
        }
        //검색결과 없음
        List<CommunitySelVo> list = mapper.selCommunity(dto);
        if (list.isEmpty()) {
            throw new RestApiException(SEARCH_COMMUNITY);
        }
        List<Integer> iboard = new ArrayList<>();
        Map<Integer, CommunitySelVo> boardMap = new HashMap<>();
        int boardAllCount = mapper.selCommunityCount(dto.getSearch());
        for (CommunitySelVo vo : list) {
            iboard.add(vo.getIboard());
            boardMap.put(vo.getIboard(), vo);
            vo.setBoardAllCount(boardAllCount);
        }

        List<CommunityPicsVo> pics = mapper.selPicCommunity(iboard);
        for (CommunityPicsVo pic : pics) {
            boardMap.get(pic.getIboard()).getPics().add(pic.getPic());
        }
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setBoardNum(boardAllCount - dto.getStartIdx() - i);
        }
        return list;
    }


    //커뮤니티 상세보기
    public CommunityDetailVo getDetailCommunity(int iboard) {
        long iuser;
        try {
            iuser= authenticationFacade.getLoginUserPk();
        } catch(Exception e) {
            iuser = 0;
        }

        CommunityModel entity = mapper.entityCommunity(iboard);
        if (entity == null) {
            throw new RestApiException(VALID_BOARD);
        }
        CommunitySelBeAfDto bDto = mapper.beforeTitle(iboard);
        CommunitySelBeAfDto aDto = mapper.afterTitle(iboard);
        CommunityDetailVo vo = mapper.selDetailCommunity(iboard,(int)iuser);
        List<CommunityBySelPicsDto> pics = mapper.selByCommunityPics(iboard);
        vo.setPics(pics);
        List<CommunityCommentVo> comments = mapper.selCommunityComments(iboard);
        vo.setComments(comments);
        vo.setBe(bDto);
        vo.setAf(aDto);
        return vo;
    }


    //커뮤니티 삭제
    @Transactional
    public ResVo delCommunity(CommunityDelDto dto) {
        //Integer check = mapper.checkCommunity(dto.getIboard());
        CommunityModel model = mapper.entityCommunity(dto.getIboard());
        //게시글 여부 확인
        if (model == null) {
            throw new RestApiException(AuthErrorCode.NOT_COMMUNITY_CHECK);
        }
        //본인 게시글 확인
        if(model.getIuser() != authenticationFacade.getLoginUserPk()) {
            throw new RestApiException(NOT_COMMUNITY_ENTITY);
        }
        String target = "/community/" + dto.getIboard();
        myFileUtils.delFolderTrigger(target);
        dto.setIuser((int)authenticationFacade.getLoginUserPk());

        CommunityEntity communityEntity = communityRepository.getReferenceById((long)dto.getIboard());
        communityRepository.delete(communityEntity);

//        mapper.delCommunityAllComment(dto);
//        mapper.delCommunityDel(dto.getIboard());
//        mapper.delCommunity(dto);
        return new ResVo(SUCCESS);
    }

    //커뮤니티 댓글 등록
    @Transactional
    public ResVo postCommunityComment(CommunityCommentInsDto dto) {
        CommunityEntity communityEntity = new CommunityEntity();
        CommunityCommentEntity commentEntity = new CommunityCommentEntity();
        communityEntity.setIboard(dto.getIboard());
        commentEntity.setUserEntity(userRepository.getReferenceById(authenticationFacade.getLoginUserPk()));
        commentEntity.setContents(dto.getContents());
        commentEntity.setCommunityEntity(communityEntity);
        communityCommentRepository.save(commentEntity);

        return new ResVo(SUCCESS);
    }
//    public ResVo postCommunityComment(CommunityCommentInsDto dto) {
//        dto.setIuser((int)authenticationFacade.getLoginUserPk());
////        //내용을 입력하지 않는 경우
////        if(dto.getContents() == null) {
////            throw new RestApiException(AuthErrorCode.NOT_CONTENT);
////        }
//        return new ResVo(mapper.insCommunityComment(dto));
//    }

    //커뮤니티 댓글 삭제
    @Transactional
    public ResVo delCommunityComment(CommunityCommentDelDto dto) {
        CommunityCommentEntity commentEntity = communityCommentRepository.getReferenceById(dto.getIcomment());
        UserEntity userEntity = new UserEntity();
        userEntity.setIuser(authenticationFacade.getLoginUserPk());
        commentEntity.setIcomment(dto.getIcomment());
        if(commentEntity.getUserEntity().getIuser() != authenticationFacade.getLoginUserPk()) {
            throw new RestApiException(NOT_COMMUNITY_ENTITY);
        }
        communityCommentRepository.delete(commentEntity);

        return new ResVo(SUCCESS);
    }

    //커뮤니티 좋아요 삽입 시 1 해제 시 0
    @Transactional
    public ResVo favCommunity(CommunityInsFavDto dto) {
        CommunityFavIds ids = new CommunityFavIds();
        ids.setIuser(authenticationFacade.getLoginUserPk());
        ids.setIboard((long)dto.getIboard());

        AtomicInteger atomic = new AtomicInteger(FAIL);
        communityFavRepository
                .findById(ids)
                .ifPresentOrElse( entity -> communityFavRepository.delete(entity), () -> {
                    atomic.set(SUCCESS);
                    CommunityFavEntity saveFavEntity = new CommunityFavEntity();
                    saveFavEntity.setCommunityFavIds(ids);
                    UserEntity userEntity = userRepository.getReferenceById((long)authenticationFacade.getLoginUserPk());
                    CommunityEntity communityEntity = communityRepository.getReferenceById((long)dto.getIboard());
                    saveFavEntity.setUserEntity(userEntity);
                    saveFavEntity.setCommunityEntity(communityEntity);
                    communityFavRepository.save(saveFavEntity);
                });
        return new ResVo(atomic.get());
    }
//    public ResVo favCommunity(CommunityInsFavDto dto) {
//        dto.setIuser(authenticationFacade.getLoginUserPk());
//        int delCommunityFav = mapper.delCommunityFav(dto);
//        if(delCommunityFav == 1) {
//            return new ResVo(0);
//        }
//        mapper.insCommunityFav(dto);
//        return new ResVo(1);
//    }

    //커뮤니티 게시글 신고
    @Transactional
    public ResVo reportCommunity(CommunityReportDto dto) {

        CommunityCountIds ids = new CommunityCountIds();
        ids.setIuser(authenticationFacade.getLoginUserPk());
        ids.setIboard(dto.getIboard());
        ReportEntity reportEntity = communityReportRepository.getReferenceById(dto.getIreport());

        Optional<CommunityCountEntity> optEntity = communityCountRepository.findByCommunityCountIds(ids);
        //신고 중복 안 되도록 처리
        if(optEntity.isPresent()) {
            throw new RestApiException(REPORT_COMMUNITY_ENTITY);
        }
        UserEntity userEntity = userRepository.getReferenceById(authenticationFacade.getLoginUserPk());
        Optional<CommunityEntity> optionalCommunity = communityRepository.findAllByIboard(dto.getIboard());
        //없는 게시물 신고불가 처리
        CommunityEntity communityEntity = optionalCommunity
                .orElseThrow(() -> new RestApiException(NOT_COMMUNITY_CHECK));
        //본인 게시글 신고 안 되도록 처리
        if(communityEntity.getUserEntity().getIuser() == authenticationFacade.getLoginUserPk()) {
            throw new RestApiException(REPORT_COMMUNITY_MYUSER);
        }
        //없는 신고 pk를 신고 하였을 때
        if(mapper.reportEntity(dto.getIreport().intValue()) == null) {
            throw new RestApiException(REPORT_ENTITY);
        }

        CommunityCountEntity countEntity = new CommunityCountEntity();
        countEntity.setCommunityCountIds(ids);
        countEntity.setIreport(reportEntity);
        countEntity.setUserEntity(userEntity);
        countEntity.setCommunityEntity(communityEntity);
        communityCountRepository.save(countEntity);


        communityEntity.setCount(communityEntity.getCount() + 1);
        communityRepository.save(communityEntity);



        return new ResVo(SUCCESS);
    }

    //커뮤니티 댓글 신고
    @Transactional
    public ResVo reportcomment(CommentReportDto dto) {
        CommunityCommentCountIds ids = new CommunityCommentCountIds();
        ids.setIuser(authenticationFacade.getLoginUserPk());
        ids.setIcomment(dto.getIcomment());
        ReportEntity reportEntity = communityReportRepository.getReferenceById(dto.getIreport());

        Optional<CommunityCommentCountEntity> optEntity = communityCommentCountRepository.findByCommunityCommentCountIds(ids);
        if(optEntity.isPresent()) {
            throw new RestApiException(REPORT_COMMUNITY_ENTITY);
        }

        UserEntity userEntity = userRepository.getReferenceById(authenticationFacade.getLoginUserPk());
        CommunityCommentEntity communityCommentEntity = communityCommentRepository.getReferenceById(dto.getIcomment());

        if(communityCommentEntity.getUserEntity().getIuser() == authenticationFacade.getLoginUserPk()) {
            throw new RestApiException(REPORT_COMMUNITY_MYUSER);
        }
        CommunityCommentCountEntity commentCountEntity = new CommunityCommentCountEntity();
        commentCountEntity.setUserEntity(userEntity);
        commentCountEntity.setCommunityCommentEntity(communityCommentEntity);
        commentCountEntity.setCommunityCommentCountIds(ids);
        commentCountEntity.setIreport(reportEntity);
        communityCommentCountRepository.save(commentCountEntity);

        communityCommentEntity.setCount(communityCommentEntity.getCount() + 1);
        communityCommentRepository.save(communityCommentEntity);

        return new ResVo(SUCCESS);
    }
}
