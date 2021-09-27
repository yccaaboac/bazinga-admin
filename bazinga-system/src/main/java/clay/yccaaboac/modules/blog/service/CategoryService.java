package clay.yccaaboac.modules.blog.service;

import clay.yccaaboac.modules.blog.domain.Category;
import clay.yccaaboac.modules.blog.service.dto.CategoryDto;
import clay.yccaaboac.modules.blog.service.dto.CategoryQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    /**
     * 查询所有类别
     * @return /
     */
    List<CategoryDto> queryAll();
    /**
     * 条件查询
     * @param criteria 条件
     * @param pageable 分页参数
     * @return /
     */
    Object queryAll(CategoryQueryCriteria criteria, Pageable pageable);
    /**
     * 新增类别
     * @param resources
     */
    void create(Category resources);
    /**
     * 修改类别
     * @param category
     */
    void update(Category category);

    /**
     * 删除类别
     * @param ids
     */
    void delete(Set<Long> ids);
}
