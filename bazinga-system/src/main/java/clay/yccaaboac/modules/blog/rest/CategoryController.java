package clay.yccaaboac.modules.blog.rest;


import clay.yccaaboac.modules.blog.domain.Category;
import clay.yccaaboac.modules.blog.service.CategoryService;
import clay.yccaaboac.modules.blog.service.dto.CategoryDto;
import clay.yccaaboac.modules.blog.service.dto.CategoryQueryCriteria;
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


@Api(tags = "博客：类别管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @ApiOperation("查询类别")
    @GetMapping
//    @PreAuthorize("@el.check('user:list')")
    public ResponseEntity<Object> query(CategoryQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(categoryService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @ApiOperation("查询全部类别")
    @GetMapping(value = "/all")
//    @PreAuthorize("@el.check('user:list','dept:list')")
    public ResponseEntity<Object> query() throws Exception {
        List<CategoryDto> categoryDto = categoryService.queryAll();
        return new ResponseEntity<>(PageUtil.toPage(categoryDto, categoryDto.size()), HttpStatus.OK);
    }

    @ApiOperation("新增类别")
    @PostMapping
    public ResponseEntity<Object> create(@Validated @RequestBody Category resources) {
        categoryService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation("修改类别")
    @PutMapping
    public ResponseEntity<Object> update(@Validated(Category.Update.class) @RequestBody Category category) throws Exception {
        categoryService.update(category);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation("删除类别")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Set<Long> ids){
        categoryService.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
