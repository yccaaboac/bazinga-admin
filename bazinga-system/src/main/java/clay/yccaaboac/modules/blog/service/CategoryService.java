package clay.yccaaboac.modules.blog.service;

import clay.yccaaboac.modules.blog.service.dto.CategoryDto;
import clay.yccaaboac.modules.blog.service.dto.CategoryQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    /**
     * 查询所有分类
     * @return /
     */
    List<CategoryDto> queryAll();

    Object queryAll(CategoryQueryCriteria criteria, Pageable pageable);
}
