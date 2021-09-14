package clay.yccaaboac.modules.blog.rest;


import clay.yccaaboac.modules.blog.service.SortService;
import clay.yccaaboac.modules.blog.service.TagService;
import clay.yccaaboac.modules.blog.service.dto.SortDto;
import clay.yccaaboac.modules.blog.service.dto.SortQueryCriteria;
import clay.yccaaboac.modules.blog.service.dto.TagDto;
import clay.yccaaboac.modules.blog.service.dto.TagQueryCriteria;
import clay.yccaaboac.utils.PageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(tags = "博客：类别管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sorts")
public class SortController {
    private final SortService sortService;

    @ApiOperation("查询分类")
    @GetMapping(value = "/all")
//    @PreAuthorize("@el.check('user:list','dept:list')")
    public ResponseEntity<Object> query(SortQueryCriteria criteria) throws Exception {
        List<SortDto> sortDto = sortService.queryAll(criteria, true);
        return new ResponseEntity<>(PageUtil.toPage(sortDto, sortDto.size()), HttpStatus.OK);
    }
}
