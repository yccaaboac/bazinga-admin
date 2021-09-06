package clay.yccaaboac.modules.blog.rest;

import clay.yccaaboac.modules.blog.domain.Blog;
import clay.yccaaboac.modules.blog.service.BlogService;
import clay.yccaaboac.modules.blog.service.dto.BlogQueryCriteria;
import clay.yccaaboac.modules.system.service.dto.UserQueryCriteria;
import clay.yccaaboac.utils.PageUtil;
import clay.yccaaboac.utils.SecurityUtils;
import cn.hutool.core.collection.CollectionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
//    @PreAuthorize("@el.check('user:list')")
    public ResponseEntity<Object> query(BlogQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(blogService.queryAll(criteria, pageable), HttpStatus.OK);
    }
}
