package clay.yccaaboac.modules.news.rest;


import clay.yccaaboac.modules.blog.rest.BlogController;
import clay.yccaaboac.modules.news.service.CommentService;
import clay.yccaaboac.modules.news.service.dto.CommentDto;
import clay.yccaaboac.modules.news.service.dto.CommentQueryCriteria;
import clay.yccaaboac.utils.PageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = "消息：评论管理")
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;
    private final BlogController blogController;

    @GetMapping
    @ApiOperation("查询评论")
    @PreAuthorize("@el.check('comment:list')")
    public ResponseEntity<Object> query(CommentQueryCriteria criteria) throws Exception {
        List<CommentDto> commentDtoList = commentService.queryAll(criteria, true);
        return new ResponseEntity<>(PageUtil.toPage(commentDtoList, commentDtoList.size()), HttpStatus.OK);
    }
}
