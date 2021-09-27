package clay.yccaaboac.modules.system.service;

import clay.yccaaboac.modules.system.domain.Dict;
import clay.yccaaboac.modules.system.service.dto.DictDto;
import clay.yccaaboac.modules.system.service.dto.DictQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface DictService {

    /**
     * 分页查询
     * @param criteria 条件
     * @param pageable 分页参数
     * @return /
     */
    Map<String,Object> queryAll(DictQueryCriteria criteria, Pageable pageable);
    /**
     * 查询全部数据
     * @param dict /
     * @return /
     */
    List<DictDto> queryAll(DictQueryCriteria dict);

    /**
     * 创建
     * @param resources /
     * @return /
     */
    void create(Dict resources);

    /**
     * 编辑
     * @param resources /
     */
    void update(Dict resources);

    /**
     * 删除
     * @param ids /
     */
    void delete(Set<Long> ids);
}
