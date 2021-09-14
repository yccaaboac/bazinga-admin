package clay.yccaaboac.modules.system.rest;

import clay.yccaaboac.modules.system.service.DictDetailService;
import clay.yccaaboac.modules.system.service.dto.DictDetailDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@Api(tags = "系统：字典详情管理")
@RequestMapping("/api/dictDetail")
public class DictDetailController {

    private final DictDetailService dictDetailService;

    @ApiOperation("根据字典名称查询多个字典详情")
    @PostMapping(value = "/getListByDictTypeList")
    public ResponseEntity<Object> getDictDetailMaps(@RequestBody List<String> dictNameList){
        Map<String, List<DictDetailDto>> dictMap = new HashMap<>(16);
        for (String name : dictNameList) {
            dictMap.put(name, dictDetailService.getDictDetailByName(name));
        }
        return new ResponseEntity<>(dictMap, HttpStatus.OK);
    }
}
