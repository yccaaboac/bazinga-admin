package clay.yccaaboac.modules.monitor.rest;

import clay.yccaaboac.modules.monitor.annotation.Log;
import clay.yccaaboac.modules.monitor.service.LogService;
import clay.yccaaboac.modules.monitor.service.dto.LogQueryCriteria;
import clay.yccaaboac.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


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
