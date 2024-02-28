package com.green.gogiro.community;

import com.green.gogiro.common.ResVo;
import com.green.gogiro.community.model.*;
import com.green.gogiro.exception.AuthErrorCode;
import com.green.gogiro.exception.RestApiException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.green.gogiro.exception.AuthErrorCode.INVALID_PAGE;

@Tag(name = "고기 잡담", description = "고기 잡담 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/community")
public class CommunityController {
    private final CommunityService service;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "커뮤니티 등록",description = "커뮤니티 등록 처리<br>" +
            "--요청데이터<br>title(NOT NULL) : 제목(1~50자)<br>" +
            "contents(NOT NULL) : 내용(1~300자)<br>" +
            "pics : 사진(최대 5장까지 등록가능)<br>" +
            "--응답데이터<br>" +
            "iboard : 보드pk<br>" +
            "pics : 사진리스트<br>" +
            "사진 5장 초과 등록 시 에러메세지 : 사진이 5장 초과 입니다.<br>" +
            "총 관리자 로그인 후 커뮤니티 등록 시 announce(1)공지로 등록<br>" +
            "가게관리자, 유저 로그인 후 커뮤니티 등록 시 announce(0)로 커뮤니티 게시글 등록")
    public CommunityPicsInsVo postCommunity(@RequestPart(required = false) List<MultipartFile> pics
            , @RequestPart @Valid CommunityInsDto dto) {
        //사진을 5장 초과했을 경우
        if(pics!=null){
            dto.setFiles(pics);
            if(dto.getFiles().size() > 5){
                throw new RestApiException(AuthErrorCode.SIZE_PHOTO);
            }
        }
        return service.insCommunity(pics, dto);
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "커뮤니티 수정", description = "커뮤니티 수정 처리<br>" +
            "--요청데이터<br>" +
            "iboard : 보드pk<br>" +
            "icommuPics : 삭제할 사진 pk<br>" +
            "title(NOT NULL) : 제목(1~50자)<br>" +
            "contents(NOT NULL) : 내용(1~300자)<br>" +
            "pics : 사진(최대 5장까지 등록가능)<br>" +
            "--응답데이터<br>" +
            "iboard : 보드pk<br>" +
            "pics : 사진리스트<br>" +
            "본인 게시글 외 수정 시 에러메세지 : 다른 유저의 게시글입니다.<br>" +
            "본인 게시글이 없을때 에러메세지 : 등록된 글을 찾을 수 없습니다.<br>" +
            "사진 5장 초과 등록 시 에러메세지 : 사진이 5장 초과 입니다.")
    public CommunityPicsInsVo putCommunity(@RequestPart(required = false) List<MultipartFile> pics
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
            "filter : 0(최신순), 1(추천순)<br>" +
            "--응답데이터<br>" +
            "iboard : 커뮤니티pk<br>" +
            "boardNum : 게시글번호<br>" +
            "iuser : 유저pk<br>" +
            "writerName : 작성자 이름<br>" +
            "writerPic : 작성자 프로필 사진<br>" +
            "title : 제목<br>" +
            "contents : 내용<br>" +
            "count : 신고 횟수<br>" +
            "createdAt : 작성일<br>" +
            "pics : 사진리스트<br>" +
            "boardAllCount : 게시글 총 갯수<br>" +
            "totalfav : 좋아요 총 갯수" +
            "신고횟수 3회이상 자동블러처리<br>" +
            "filter 최신순(0), 좋아요 순(1)외에 입력 시 : 잘못된 페이지가 입력되었습니다.")
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
            "comments : 댓글리스트<br>" +
            "게시글이 없을때 : 등록된 글을 찾을 수 없습니다.")
    public CommunityDetailVo getDetailCommunity(@PathVariable int iboard) {
        return service.getDetailCommunity(iboard);
    }

    @DeleteMapping()
    @Operation(summary = "커뮤니티 삭제",description = "커뮤니티 삭제 처리<br>" +
            "--요청데이터<br>" +
            "iboard : 커뮤니티pk<br>" +
            "--응답데이터<br>" +
            "result : 1(성공), 에러메세지(실패)<br>" +
            "본인 게시글이 없을때 에러메세지 : 등록된 글을 찾을 수 없습니다.<br>" +
            "다른 유저 게시글 삭제 시 에러메세지 : 다른 유저의 게시글입니다.")
    public ResVo delCommunity(CommunityDelDto dto) {
        return service.delCommunity(dto);
    }

    @PostMapping("/comment")
    @Operation(summary = "커뮤니티 댓글 작성",description = "커뮤니티 댓글 작성 처리<br>" +
            "--요청데이터<br>" +
            "iboard : 커뮤니티pk<br>" +
            "contents : 댓글내용(1~50자)<br>" +
            "--응답데이터<br>" +
            "result : 1(성공)<br>" +
            "없는 iboard pk 등록 시 : 등록된 글을 찾을 수 없습니다.")
    public ResVo postCommunityComment(@Valid @RequestBody CommunityCommentInsDto dto) {
        return service.postCommunityComment(dto);
    }

    @DeleteMapping("/comment")
    @Operation(summary = "커뮤니티 댓글 삭제",description = "커뮤니티 댓글 삭제 처리<br>" +
            "--요청데이터<br>" +
            "icomment : 댓글pk<br>" +
            "--응답데이터<br>" +
            "result : 1(성공)<br>" +
            "없는 icomment pk 삭제 시 : 등록된 글을 찾을 수 없습니다.")
    public ResVo delCommunityComment(@RequestBody CommunityCommentDelDto dto) {
        return service.delCommunityComment(dto);
    }

    @PostMapping("/fav")
    @Operation(summary = "커뮤니티 추천", description = "커뮤니티 추천 처리<br>" +
            "--요청데이터<br>" +
            "iboard : 커뮤니티pk<br>" +
            "--응답데이터<br>" +
            "result : <select>" +
            "    <option>1(추천)</option>" +
            "    <option>0(추천 취소)</option>" +
            "</select>")
    public ResVo favCommunity(@RequestBody CommunityInsFavDto dto) {
        return service.favCommunity(dto);
    }

    @PostMapping("/report")
    @Operation(summary = "커뮤니티 신고", description = "커뮤니티 신고 처리<br>" +
            "--요청데이터<br>" +
            "iboard : 커뮤니티pk<br>" +
            "ireport : 신고pk<br>" +
            "1. 욕설/인신공격<br>" +
            "2. 음란물<br>" +
            "3. 영리목적/홍보성<br>" +
            "4. 개인정보<br>" +
            "5. 게시글 도배<br>" +
            "6. 기타<br>" +
            "--응답데이터<br>" +
            "result : 1(성공), 중복신고(이미 신고된 게시글입니다)" +
            ", 본인게시글 신고(본인 게시글은 신고가 불가능합니다)<br>" +
            "신고pk 외 등록 시 : 신고 종류를 찾을 수 없습니다.")
    public ResVo reportCommunity(CommunityReportDto dto) {
        return service.reportCommunity(dto);
    }

    @PostMapping("/comment/report")
    @Operation(summary = "커뮤니티 댓글 신고", description = "커뮤니티 댓글 신고 처리<br>" +
            "--요청데이터<br>" +
            "icomment : 댓글pk<br>" +
            "ireport : 신고pk<br>" +
            "1. 욕설/인신공격<br>" +
            "2. 음란물<br>" +
            "3. 영리목적/홍보성<br>" +
            "4. 개인정보<br>" +
            "5. 게시글 도배<br>" +
            "6. 기타<br>" +
            "--응답데이터<br>" +
            "result : 1(성공), 중복신고(이미 신고된 게시글입니다)" +
            ", 본인댓글 신고(본인 게시글은 신고가 불가능합니다)<br>" +
            "신고pk 외 등록 시 : 신고 종류를 찾을 수 없습니다.")
    public ResVo reportCommentCommunity(CommentReportDto dto) {
        return service.reportcomment(dto);
    }

}
