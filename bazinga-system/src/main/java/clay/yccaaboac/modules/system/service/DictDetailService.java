package clay.yccaaboac.modules.system.service;


import clay.yccaaboac.modules.system.service.dto.DictDetailDto;
import clay.yccaaboac.modules.system.service.dto.DictDetailQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface DictDetailService {

    List<DictDetailDto> getDictDetailByName(String name);

    /**
     * 分页查询
     * @param criteria 条件
     * @param pageable 分页参数
     * @return /
     */
    Map<String,Object> queryAll(DictDetailQueryCriteria criteria, Pageable pageable);
}
