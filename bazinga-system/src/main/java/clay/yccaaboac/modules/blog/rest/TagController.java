package clay.yccaaboac.modules.blog.rest;


import clay.yccaaboac.modules.blog.service.TagService;
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

@Api(tags = "博客：标签管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tags")
public class TagController {
    private final TagService tagService;

    @ApiOperation("查询标签")
    @GetMapping(value = "/all")
//    @PreAuthorize("@el.check('user:list','dept:list')")
    public ResponseEntity<Object> query(TagQueryCriteria criteria) throws Exception {
        List<TagDto> tagDtos = tagService.queryAll(criteria, true);
        return new ResponseEntity<>(PageUtil.toPage(tagDtos, tagDtos.size()), HttpStatus.OK);
    }
}
