package clay.yccaaboac.modules.blog.service;

import clay.yccaaboac.modules.blog.domain.Blog;
import clay.yccaaboac.modules.blog.service.dto.BlogQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface BlogService {
    /**
     * 查询全部
     * @param criteria 条件
     * @param pageable 分页参数
     * @return /
     */
    Object queryAll(BlogQueryCriteria criteria, Pageable pageable);

    /**
     * 新增博客
     * @param resources
     */
    void create(Blog resources);

    /**
     * 编辑博客
     * @param resources
     */
    void update(Blog resources) throws Exception;

    /**
     * 删除博客
     * @param ids /
     */
    void delete(Set<Long> ids);

    /**
     * 修改发布状态
     * @param id
     * @param isPublish
     */
    void changeRelease(Long id, String isPublish);
}
