package clay.yccaaboac.modules.blog.rest;


import clay.yccaaboac.modules.blog.domain.Tag;
import clay.yccaaboac.modules.blog.service.TagService;
import clay.yccaaboac.modules.blog.service.dto.TagDto;
import clay.yccaaboac.modules.blog.service.dto.TagQueryCriteria;
import clay.yccaaboac.utils.PageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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

    @ApiOperation("查询标签")
    @GetMapping
//    @PreAuthorize("@el.check('user:list')")
    public ResponseEntity<Object> query(TagQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(tagService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @ApiOperation("新增标签")
    @PostMapping
    public ResponseEntity<Object> create(@Validated @RequestBody Tag resources) {
        tagService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation("修改标签")
    @PutMapping
    public ResponseEntity<Object> update(@Validated(Tag.Update.class) @RequestBody Tag category) throws Exception {
        tagService.update(category);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation("删除标签")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Set<Long> ids){
        tagService.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
