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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/operationLog")
@Api(tags = "系统：操作日志管理")
public class OperationLogController {

    private final LogService logService;

    @GetMapping
    @ApiOperation("操作日志查询")
    public ResponseEntity<Object> query(LogQueryCriteria criteria, Pageable pageable){
        criteria.setLogType("INFO");
        return new ResponseEntity<>(logService.queryAll(criteria,pageable), HttpStatus.OK);
    }


    @Log("删除所有INFO日志")
    @ApiOperation("删除所有INFO日志")
    @DeleteMapping
    public ResponseEntity<Object> delAllInfoLog(){
        logService.delAllByInfo();
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping(value = "/user")
//    @ApiOperation("用户日志查询")
//    public ResponseEntity<Object> queryUserLog(LogQueryCriteria criteria, Pageable pageable){
//        criteria.setLogType("INFO");
//        criteria.setBlurry(SecurityUtils.getCurrentUsername());
//        return new ResponseEntity<>(logService.queryAllByUser(criteria,pageable), HttpStatus.OK);
//    }
}
