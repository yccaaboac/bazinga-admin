package clay.yccaaboac.modules.blog.service;

import clay.yccaaboac.modules.blog.service.dto.TagDto;
import clay.yccaaboac.modules.blog.service.dto.TagQueryCriteria;
import clay.yccaaboac.modules.system.service.dto.DeptQueryCriteria;

import java.util.List;

public interface TagService {
    /**
     * 查询所有数据
     * @param criteria 条件
     * @param isQuery /
     * @throws Exception /
     * @return /
     */
    List<TagDto> queryAll(TagQueryCriteria criteria, Boolean isQuery) throws Exception;
}
