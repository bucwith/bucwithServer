package com.bucwith.controller.star;


import com.bucwith.common.CommController;
import com.bucwith.dto.star.StarReqDto;
import com.bucwith.dto.star.StarResDto;
import com.bucwith.service.star.StarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/star")
public class StarController extends CommController {

    private final StarService starService;

    /*
     * Star(응원별) 등록
     * Request Data : StarReqDto
     * Response Data : 등록한 Star(응원별) 반환
     */
    @PostMapping
    public ResponseEntity register(@Validated @RequestBody StarReqDto reqDto) {
        return SuccessReturn(starService.register(reqDto));
    }

    /*
     * Star(응원별) 조회
     * Request Data : bucketId
     * iconCode -> Icon 조회 및 반환 필요.
     * Response Data : StarResDto 반환 (totalCnt, stars)
     */
    @GetMapping
    public ResponseEntity select(@RequestParam Integer bucketId,
                                 @RequestParam Integer currentPage,
                                 @RequestParam Integer starCnt,
                                 @RequestParam Boolean isDesc) {
        return SuccessReturn(new StarResDto(
                starService.countStarByBucketId(bucketId), // totalCnt
                starService.getStarByBucketId(bucketId, currentPage, starCnt, isDesc))); // stars
    }

    /*
     * Star(응원별) 삭제
     * Request Data : starId (삭제할 star Id)
     * Response Data : ""
     */
    @DeleteMapping("/{starId}")
    public ResponseEntity delete(@PathVariable Integer starId) {
        starService.remove(starId);

        return SuccessReturn();
    }
}