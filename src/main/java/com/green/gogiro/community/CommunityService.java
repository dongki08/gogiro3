package com.green.gogiro.community;

import static com.green.gogiro.common.Const.*;
import static com.green.gogiro.exception.AuthErrorCode.*;


import com.green.gogiro.common.Const;
import com.green.gogiro.common.MyFileUtils;
import com.green.gogiro.common.ResVo;
import com.green.gogiro.community.model.*;
import com.green.gogiro.exception.AuthErrorCode;
import com.green.gogiro.exception.RestApiException;
import com.green.gogiro.security.AuthenticationFacade;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
@Slf4j
public class CommunityService {
    private final CommunityMapper mapper;
    private final MyFileUtils myFileUtils;
    private final AuthenticationFacade authenticationFacade;

    @Transactional
    public CommunityPicsInsVo insCommunity(CommunityInsDto dto) {
        dto.setIuser(authenticationFacade.getLoginUserPk());
//        //제목을 입력하지 않는 경우
//        if(dto.getTitle() == null || Pattern.matches(Const.REGEXP_PATTERN_SPACE_CHAR, dto.getTitle())) {
//            throw new RestApiException(AuthErrorCode.NOT_COMMUNITY_TITLE);
//        }
//        //내용을 입력하지 않는 경우
//        if(dto.getContents() == null || Pattern.matches(Const.REGEXP_PATTERN_SPACE_CHAR, dto.getContents())) {
//            throw new RestApiException(AuthErrorCode.NOT_CONTENT);
//        }
        mapper.insCommunity(dto);
        if (dto.getFiles() != null) {
            String target = "/community/" + dto.getIboard();
            for (MultipartFile file : dto.getFiles()) {
                String saveFileNm = myFileUtils.transferTo(file, target);
                dto.getPics().add(saveFileNm);
            }
            mapper.insCommunityPics(dto);
        }

        //auto_increment 0 값일때
        if (dto.getIboard() == 0) {
            throw new RestApiException(AuthErrorCode.NOT_COMMUNITY);
        }
        CommunityPicsInsVo vo = CommunityPicsInsVo.builder()
                .iboard(dto.getIboard())
                .pics(dto.getPics())
                .build();
        return vo;
    }

    @Transactional
    public CommunityPicsInsVo updCommunity(CommunityUpdDto dto) {
        Integer check = mapper.checkCommunity(dto.getIboard());
        List<CommunityBySelPicsDto> bDto = mapper.selByCommunityPics(dto.getIboard());

        int boardSize = bDto != null ? bDto.size() : 0;
        int fileSize = dto.getFiles() != null ? dto.getFiles().size() : 0;
        int delSize = dto.getIcommuPics() != null ? dto.getIcommuPics().size() : 0;
        int totalSize = boardSize + fileSize - delSize;

        if (check == null) {
            throw new RestApiException(AuthErrorCode.NOT_COMMUNITY_CHECK);
        } else if (totalSize > 5) {
            throw new RestApiException(SIZE_PHOTO);
        }
        dto.setIuser(authenticationFacade.getLoginUserPk());
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
        int count = mapper.selCommunityCount(dto.getSearch());
        for (CommunitySelVo vo : list) {
            iboard.add(vo.getIboard());
            boardMap.put(vo.getIboard(), vo);
            vo.setCount(count);
        }

        List<CommunityPicsVo> pics = mapper.selPicCommunity(iboard);
        for (CommunityPicsVo pic : pics) {
            boardMap.get(pic.getIboard()).getPics().add(pic.getPic());
        }
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setBoardNum(count - dto.getStartIdx() - i);
        }
        return list;
    }

    public CommunityDetailVo getDetailCommunity(int iboard) {
        CommunityEntity entity = mapper.entityCommunity(iboard);
        if (entity == null) {
            throw new RestApiException(VALID_BOARD);
        }
        CommunitySelBeAfDto bDto = mapper.beforeTitle(iboard);
        CommunitySelBeAfDto aDto = mapper.afterTitle(iboard);
        CommunityDetailVo vo = mapper.selDetailCommunity(iboard);
        List<CommunityBySelPicsDto> pics = mapper.selByCommunityPics(iboard);
        vo.setPics(pics);
        List<CommunityCommentVo> comments = mapper.selCommunityComments(iboard);
        vo.setComments(comments);
        vo.setBe(bDto);
        vo.setAf(aDto);
        return vo;
    }

    @Transactional
    public ResVo delCommunity(CommunityDelDto dto) {
        Integer check = mapper.checkCommunity(dto.getIboard());
        //게시글 여부 확인
        if (check == null) {
            throw new RestApiException(AuthErrorCode.NOT_COMMUNITY_CHECK);
        }
        String target = "/community/" + dto.getIboard();
        myFileUtils.delFolderTrigger(target);
        dto.setIuser(authenticationFacade.getLoginUserPk());
        mapper.delCommunityAllComment(dto);
        mapper.delCommunityDel(dto.getIboard());
        mapper.delCommunity(dto);
        return new ResVo(SUCCESS);
    }


    public ResVo postCommunityComment(CommunityCommentInsDto dto) {
        dto.setIuser(authenticationFacade.getLoginUserPk());
//        //내용을 입력하지 않는 경우
//        if(dto.getContents() == null) {
//            throw new RestApiException(AuthErrorCode.NOT_CONTENT);
//        }
        return new ResVo(mapper.insCommunityComment(dto));
    }

    public ResVo delCommunityComment(CommunityCommentDelDto dto) {
        dto.setIuser(authenticationFacade.getLoginUserPk());
        return new ResVo(mapper.delCommunityComment(dto));
    }

    //커뮤니티 좋아요 삽입 시 1 해제 시 0
    public ResVo favCommunity(CommunityInsFavDto dto) {
        int delCommunityFav = mapper.delCommunityFav(dto);
        if(delCommunityFav == 1) {
            return new ResVo(0);
        }
        mapper.insCommunityFav(dto);
        return new ResVo(1);
    }
}
