package clay.yccaaboac.modules.picture.service;

import clay.yccaaboac.modules.picture.domain.PicCategory;
import clay.yccaaboac.modules.picture.service.dto.PicCategoryDto;
import clay.yccaaboac.modules.picture.service.dto.PicCategoryQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface PicCategoryService {
    /**
     * 查询所有图片类别
     * @return /
     */
    List<PicCategoryDto> queryAll();
    /**
     * 条件查询
     * @param criteria 条件
     * @param pageable 分页参数
     * @return /
     */
    Object queryAll(PicCategoryQueryCriteria criteria, Pageable pageable);
    /**
     * 新增图片类别
     * @param resources
     */
    void create(PicCategory resources);
    /**
     * 修改图片类别
     * @param category
     */
    void update(PicCategory category);

    /**
     * 删除图片类别
     * @param ids
     */
    void delete(Set<Long> ids);
}
