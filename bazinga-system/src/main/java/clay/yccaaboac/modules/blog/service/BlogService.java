package clay.yccaaboac.modules.blog.service;

import clay.yccaaboac.modules.blog.service.dto.BlogQueryCriteria;
import clay.yccaaboac.modules.system.service.dto.UserQueryCriteria;
import org.springframework.data.domain.Pageable;

public interface BlogService {
    /**
     * 查询全部
     * @param criteria 条件
     * @param pageable 分页参数
     * @return /
     */
    Object queryAll(BlogQueryCriteria criteria, Pageable pageable);
}
