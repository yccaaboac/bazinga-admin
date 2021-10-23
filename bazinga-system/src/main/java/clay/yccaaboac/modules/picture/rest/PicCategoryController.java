package clay.yccaaboac.modules.picture.rest;

import clay.yccaaboac.modules.blog.domain.Category;
import clay.yccaaboac.modules.monitor.annotation.Log;
import clay.yccaaboac.modules.picture.domain.PicCategory;
import clay.yccaaboac.modules.picture.service.PicCategoryService;
import clay.yccaaboac.modules.picture.service.dto.PicCategoryDto;
import clay.yccaaboac.modules.picture.service.dto.PicCategoryQueryCriteria;
import clay.yccaaboac.utils.PageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Api(tags = "图片：图片类别管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/picCategories")
public class PicCategoryController {
    private final PicCategoryService picCategoryService;

    @ApiOperation("查询图片类别")
    @GetMapping
    @PreAuthorize("@el.check('picCategory:list')")
    public ResponseEntity<Object> query(PicCategoryQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(picCategoryService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @ApiOperation("查询全部图片类别")
    @GetMapping(value = "/all")
    @PreAuthorize("@el.check('picCategory:list')")
    public ResponseEntity<Object> query() {
        List<PicCategoryDto> picCategoryDto = picCategoryService.queryAll();
        return new ResponseEntity<>(PageUtil.toPage(picCategoryDto, picCategoryDto.size()), HttpStatus.OK);
    }

    @Log("新增图片类别")
    @ApiOperation("新增图片类别")
    @PreAuthorize("@el.check('picCategory:add')")
    @PostMapping
    public ResponseEntity<Object> create(@Validated @RequestBody PicCategory resources) {
        picCategoryService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Log("修改图片类别")
    @ApiOperation("修改图片类别")
    @PreAuthorize("@el.check('picCategory:edit')")
    @PutMapping
    public ResponseEntity<Object> update(@Validated(Category.Update.class) @RequestBody PicCategory picCategory) throws Exception {
        picCategoryService.update(picCategory);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除图片类别")
    @ApiOperation("删除图片类别")
    @PreAuthorize("@el.check('picCategory:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Set<Long> ids){
        picCategoryService.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}