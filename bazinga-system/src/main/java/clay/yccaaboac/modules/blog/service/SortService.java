package clay.yccaaboac.modules.blog.service;

import clay.yccaaboac.modules.blog.service.dto.SortDto;
import clay.yccaaboac.modules.blog.service.dto.SortQueryCriteria;


import java.util.List;

public interface SortService {
    /**
     * 查询所有分类
     * @param criteria 条件
     * @param isQuery /
     * @throws Exception /
     * @return /
     */
    List<SortDto> queryAll(SortQueryCriteria criteria, Boolean isQuery) throws Exception;
}
