package clay.yccaaboac.modules.blog.service;

import clay.yccaaboac.modules.blog.domain.Tag;
import clay.yccaaboac.modules.blog.service.dto.TagDto;
import clay.yccaaboac.modules.blog.service.dto.TagQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface TagService {
    /**
     * 查询所有数据
     * @param criteria 条件
     * @param isQuery /
     * @throws Exception /
     * @return /
     */
    List<TagDto> queryAll(TagQueryCriteria criteria, Boolean isQuery) throws Exception;

    /**
     * 条件查询
     * @param criteria 条件
     * @param pageable 分页参数
     * @return /
     */
    Object queryAll(TagQueryCriteria criteria, Pageable pageable);
    /**
     * 新增类别
     * @param resources
     */
    void create(Tag resources);
    /**
     * 修改类别
     * @param category
     */
    void update(Tag category);

    /**
     * 删除类别
     * @param ids
     */
    void delete(Set<Long> ids);
}
