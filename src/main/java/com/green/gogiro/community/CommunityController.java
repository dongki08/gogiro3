package com.green.gogiro.community;

import com.green.gogiro.common.ResVo;
import com.green.gogiro.community.model.*;
import com.green.gogiro.exception.AuthErrorCode;
import com.green.gogiro.exception.RestApiException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "고기 잡담", description = "고기 잡담 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/community")
public class CommunityController {
    private final CommunityService service;

    @PostMapping()
    @Operation(summary = "커뮤니티 등록",description = "커뮤니티 등록 처리<br>" +
            "--요청데이터<br>title(NOT NULL) : 제목(1~50자)<br>" +
            "contents(NOT NULL) : 내용(1~300자)<br>" +
            "pics : 사진(최대 5장까지 등록가능)<br>" +
            "--응답데이터<br>iboard : 보드pk<br>" +
            "pics : 사진리스트")
    public CommunityPicsInsVo postCommunity(@RequestPart(required = false)@Valid List<MultipartFile> pics
            , @RequestPart @Valid CommunityInsDto dto) {
        //사진을 5장 초과했을 경우
        if(pics!=null){
            dto.setFiles(pics);
            if(dto.getFiles().size() > 5){
                throw new RestApiException(AuthErrorCode.SIZE_PHOTO);
            }
        }
        return service.insCommunity(dto);
    }

    @PutMapping()
    @Operation(summary = "커뮤니티 수정", description = "커뮤니티 수정 처리<br>" +
            "--요청데이터<br>title(NOT NULL) : 제목(1~50자)<br>" +
            "contents(NOT NULL) : 내용(1~300자)<br>" +
            "pics : 사진(최대 5장까지 등록가능)<br>" +
            "--응답데이터<br>iboard : 보드pk<br>" +
            "pics : 사진리스트")
    public CommunityPicsInsVo putCommunity(@Valid @RequestPart(required = false) List<MultipartFile> pics
            ,@Valid @RequestPart CommunityUpdDto dto) {
        //사진을 5장 초과했을 경우
        if(pics!=null){
            dto.setFiles(pics);
            if(dto.getFiles().size() > 5){
                throw new RestApiException(AuthErrorCode.SIZE_PHOTO);
            }
        }
        return service.updCommunity(dto);
    }

    @GetMapping()
    @Operation(summary = "커뮤니티 리스트", description = "커뮤니티 리스트 처리<br>" +
            "--요청데이터<br>page : 페이지처리<br>" +
            "search : 제목검색<br>" +
            "filter : 0(작성자순), 1(추천순)<br>" +
            "--응답데이터<br>" +
            "iboard : 커뮤니티pk<br>" +
            "boardNum : 게시글번호<br>" +
            "iuser : 유저pk<br>" +
            "writerName : 작성자 이름<br>" +
            "writerPic : 작성자 프로필 사진<br>" +
            "title : 제목<br>" +
            "contents : 내용<br>" +
            "createdAt : 작성일<br>" +
            "pics : 사진리스트<br>" +
            "count : 게시글 총 갯수")
    public List<CommunitySelVo> getCommunity(CommunitySelDto dto) {
        return service.selCommunity(dto);
    }

    @GetMapping("{iboard}")
    @Operation(summary = "커뮤니티 상세보기", description = "커뮤니티 상세보기 처리<br>" +
            "--요청데이터<br>" +
            "iboard : 커뮤니티pk<br>" +
            "--응답데이터<br>" +
            "iboard : 커뮤니티pk<br>" +
            "iuser : 유저pk<br>" +
            "writerName : 작성자 이름<br>" +
            "writerPic : 작성자 프로필 사진<br>" +
            "title : 제목<br>" +
            "totalFav : 총 좋아요 수<br>" +
            "isFav : 좋아요 여부 1(좋아요), 0(좋아요취소)<br>" +
            "contents : 내용<br>" +
            "createdAt : 작성일<br>" +
            "pics : 사진리스트<br>" +
            "be : 이전글<br>" +
            "af : 다음글<br>" +
            "comments : 댓글리스트")
    public CommunityDetailVo getDetailCommunity(@PathVariable int iboard) {
        return service.getDetailCommunity(iboard);
    }

    @DeleteMapping()
    @Operation(summary = "커뮤니티 삭제",description = "커뮤니티 삭제 처리<br>" +
            "--요청데이터<br>" +
            "iboard : 커뮤니티pk<br>" +
            "--응답데이터<br>" +
            "result : 1(성공), 에러메세지(실패)")
    public ResVo delCommunity(CommunityDelDto dto) {
        return service.delCommunity(dto);
    }

    @PostMapping("/comment")
    @Operation(summary = "커뮤니티 댓글 작성",description = "커뮤니티 댓글 작성 처리<br>" +
            "--요청데이터<br>" +
            "iboard : 커뮤니티pk<br>" +
            "contents : 댓글내용(1~50자)<br>" +
            "--응답데이터<br>" +
            "result : 1(성공), 에러메세지(실패)")
    public ResVo postCommunityComment(@Valid @RequestBody CommunityCommentInsDto dto) {
        return service.postCommunityComment(dto);
    }

    @DeleteMapping("/comment")
    @Operation(summary = "커뮤니티 댓글 삭제",description = "커뮤니티 댓글 삭제 처리<br>" +
            "--요청데이터<br>" +
            "icomment : 댓글pk<br>" +
            "--응답데이터<br>" +
            "result : 1(성공), 에러메세지(실패)")
    public ResVo delCommunityComment(@RequestBody CommunityCommentDelDto dto) {
        return service.delCommunityComment(dto);
    }

    @PostMapping("/fav")
    @Operation(summary = "좋아요 기능", description = "좋아요 처리<br>" +
            "--요청데이터<br>" +
            "iuser : 유저pk<br>" +
            "iboard : 커뮤니티pk<br>" +
            "--응답데이터<br>" +
            "result : <select>" +
            "    <option>1(좋아요)</option>" +
            "    <option>0(좋아요취소)</option>" +
            "</select>")
    public ResVo favCommunity(@RequestBody CommunityInsFavDto dto) {
        return service.favCommunity(dto);
    }

}
