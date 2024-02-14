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
    @Operation(summary = "커뮤니티 등록",description = "커뮤니티 등록 처리")
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
    @Operation(summary = "커뮤니티 수정", description = "커뮤니티 수정 처리")
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
    @Operation(summary = "커뮤니티 리스트", description = "커뮤니티 리스트 처리")
    public List<CommunitySelVo> getCommunity(CommunitySelDto dto) {
        return service.selCommunity(dto);
    }

    @GetMapping("{iboard}")
    @Operation(summary = "커뮤니티 상세보기", description = "커뮤니티 상세보기 처리")
    public CommunityDetailVo getDetailCommunity(@PathVariable int iboard) {
        return service.getDetailCommunity(iboard);
    }

    @DeleteMapping()
    @Operation(summary = "커뮤니티 삭제",description = "커뮤니티 삭제 처리")
    public ResVo delCommunity(CommunityDelDto dto) {
        return service.delCommunity(dto);
    }

    @PostMapping("/comment")
    @Operation(summary = "커뮤니티 댓글 작성",description = "커뮤니티 댓글 작성 처리")
    public ResVo postCommunityComment(@Valid @RequestBody CommunityCommentInsDto dto) {
        return service.postCommunityComment(dto);
    }

    @DeleteMapping("/comment")
    @Operation(summary = "커뮤니티 댓글 삭제",description = "커뮤니티 댓글 삭제 처리")
    public ResVo delCommunityComment(@RequestBody CommunityCommentDelDto dto) {
        return service.delCommunityComment(dto);
    }

    @GetMapping("/fav")
    @Operation(summary = "좋아요 기능", description = "좋아요 처리(1), 좋아요 삭제(0)")
    public ResVo favCommunity(@RequestBody CommunityInsFavDto dto) {
        return service.favCommunity(dto);
    }

}
