package clay.yccaaboac.modules.blog.rest;

import clay.yccaaboac.modules.blog.domain.Blog;
import clay.yccaaboac.modules.blog.service.BlogService;
import clay.yccaaboac.modules.blog.service.dto.BlogQueryCriteria;
import clay.yccaaboac.annotation.Log;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * <p>Title:Blog</p>
 *
 * @author Administrator
 * @date 2021/8/5 23:29
 */

@Api(tags = "博客：博客管理")
@RestController
@RequestMapping("/api/blogs")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @ApiOperation("查询博客")
    @GetMapping
    @PreAuthorize("@el.check('blog:list')")
    public ResponseEntity<Object> query(BlogQueryCriteria criteria, Pageable pageable) {
        ResponseEntity<Object> objectResponseEntity = new ResponseEntity<>(blogService.queryAll(criteria, pageable), HttpStatus.OK);
        return objectResponseEntity;
    }


    @Log("新增博客")
    @ApiOperation("新增博客")
    @PreAuthorize("@el.check('blog:add')")
    @PostMapping
    public ResponseEntity<Object> create(@Validated @RequestBody Blog resources) {
        blogService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation("修改博客")
    @PreAuthorize("@el.check('blog:edit')")
    @Log("修改博客")
    @PutMapping
    public ResponseEntity<Object> update(@Validated(Blog.Update.class) @RequestBody Blog resources) throws Exception {
        blogService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("修改发布状态")
    @ApiOperation("修改发布状态")
    @PutMapping("/changeRelease")
    public ResponseEntity<Object> changeRelease(@RequestParam Long id,@RequestParam String isPublish) throws Exception {
        blogService.changeRelease(id,isPublish);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @Log("删除博客")
    @ApiOperation("删除博客")
    @PreAuthorize("@el.check('user:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Set<Long> ids){
        blogService.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
