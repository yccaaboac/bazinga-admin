package clay.yccaaboac.modules.picture.repository;

import clay.yccaaboac.modules.picture.domain.PicCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Set;


public interface PicCategoryRepository extends JpaRepository<PicCategory,Long>, JpaSpecificationExecutor<PicCategory> {
    /**
     * 根据图片类别名称查询
     * @param name
     * @return /
     */
    PicCategory findByName(String name);

    /**
     * 根据Id删除
     * @param ids /
     */
    void deleteAllByIdIn(Set<Long> ids);
}
