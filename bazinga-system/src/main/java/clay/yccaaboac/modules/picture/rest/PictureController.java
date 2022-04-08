package clay.yccaaboac.modules.picture.rest;


import clay.yccaaboac.annotation.Log;
import clay.yccaaboac.modules.blog.domain.Category;
import clay.yccaaboac.modules.picture.domain.Picture;
import clay.yccaaboac.modules.picture.service.PictureService;
import clay.yccaaboac.modules.picture.service.dto.PictureQueryCriteria;
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

@RestController
@RequiredArgsConstructor
@Api(tags = "图片：图片信息管理")
@RequestMapping("/api/pictures")
public class PictureController {

    private final PictureService pictureService;

    @ApiOperation("查询图片详情")
    @GetMapping
    public ResponseEntity<Object> query(PictureQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(pictureService.queryAll(criteria,pageable), HttpStatus.OK);
    }

    @Log("删除图片")
    @ApiOperation("删除图片")
    @PreAuthorize("@el.check('picture:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Set<Long> ids){
        pictureService.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Log("新增图片")
    @ApiOperation("新增图片")
    @PreAuthorize("@el.check('picture:add')")
    @PostMapping
    public ResponseEntity<Object> create(@Validated @RequestBody Picture resources) {
        pictureService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Log("修改图片")
    @ApiOperation("修改图片")
    @PreAuthorize("@el.check('picture:edit')")
    @PutMapping
    public ResponseEntity<Object> update(@Validated(Category.Update.class) @RequestBody Picture picture) throws Exception {
        pictureService.update(picture);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
