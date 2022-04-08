package clay.yccaaboac.rest;


import clay.yccaaboac.annotation.Log;
import clay.yccaaboac.service.LogService;
import clay.yccaaboac.service.dto.LogQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/errorLog")
@Api(tags = "系统：错误日志管理")
public class ErrorLogController {

    private final LogService logService;

    @ApiOperation("错误日志查询")
    @GetMapping
    public ResponseEntity<Object> query(LogQueryCriteria criteria, Pageable pageable){
        criteria.setLogType("ERROR");
        return new ResponseEntity<>(logService.queryAll(criteria,pageable), HttpStatus.OK);
    }

    @Log("删除所有ERROR日志")
    @ApiOperation("删除所有ERROR日志")
    @DeleteMapping
    public ResponseEntity<Object> delAllErrorLog(){
        logService.delAllByError();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation("日志异常详情查询")
//    @PreAuthorize("@el.check()")
    public ResponseEntity<Object> queryErrorDetail(@PathVariable Long id){
        return new ResponseEntity<>(logService.findByErrDetail(id), HttpStatus.OK);
    }
}
