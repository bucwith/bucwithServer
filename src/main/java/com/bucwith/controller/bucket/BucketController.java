package com.bucwith.controller.bucket;


import com.bucwith.common.CommController;
import com.bucwith.common.config.JwtService;
import com.bucwith.common.exception.BaseException;
import com.bucwith.dto.bucket.BucketModifyReqDto;
import com.bucwith.dto.bucket.BucketReqDto;
import com.bucwith.service.bucket.BucketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/bucket")
public class BucketController extends CommController {

    private final JwtService jwtService;
    private final BucketService bucketService;

    /*
     * Bucket 등록
     * Request Data : BucketReqDto(userId, contents, type)
     * Response Data : 등록한 Bucket 반환
     */
    @PostMapping
    public ResponseEntity register(@Validated @RequestBody BucketReqDto reqDto) {
        long userId = jwtService.getUserId();
        return SuccessReturn(bucketService.register(reqDto.toEntity(userId)));
    }

    /*
     * Bucket 조회
     * - Use Token
     * Request Data : null
     * Response Data : userId로 조회한 Bucket 반환
     */
    @GetMapping("/user")
    public ResponseEntity selectByUser() {
        long userId = jwtService.getUserId();
        return SuccessReturn(bucketService.getBuckets(userId));
    }

    /*
     * Bucket 조회
     * Don't Use Token
     * Request Data : bucketId
     * Response Data : bucketId 조회한 Bucket 반환 (작성자 객체도 같이 반환)
     */
    @GetMapping("/id/{bucketId}")
    public ResponseEntity selectByBucketId(@PathVariable long bucketId)  {
        return SuccessReturn(bucketService.getBucketResDto(bucketId));
    }

    /*
     * Bucket 수정
     * Request Data : BucketReqDto(bucketId, contents, type)
     * Response Data : 수정된 Bucket 반환
     */
    @PutMapping
    public ResponseEntity modify(@Validated @RequestBody BucketModifyReqDto reqDto) {
        return SuccessReturn(bucketService.modify(reqDto));
    }

    /*
     * Bucket 삭제
     * Request Data : bucketId (삭제할 bucket Id)
     * Response Data : ""
     */
    @DeleteMapping("/{bucketId}")
    public ResponseEntity delete(@PathVariable Integer bucketId) {
        bucketService.remove(bucketId);

        return SuccessReturn();
    }

    /*
     * Bucket 완료 True Or False 등록
     * Request Data : bucketId
     * Response Data : 수정된 Bucket 반환
     */
    @PostMapping("/finish/{bucketId}")
    public ResponseEntity setFinished(@PathVariable Integer bucketId) {
        return SuccessReturn(bucketService.finished(bucketId));
    }
}