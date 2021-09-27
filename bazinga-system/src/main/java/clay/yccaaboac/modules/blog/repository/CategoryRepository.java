package clay.yccaaboac.modules.blog.repository;

import clay.yccaaboac.modules.blog.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Set;

public interface CategoryRepository extends JpaRepository<Category,Long>, JpaSpecificationExecutor<Category> {
    /**
     * 根据类别名称查询
     * @param name
     * @return /
     */
    Category findByName(String name);

    /**
     * 根据Id删除
     * @param ids /
     */
    void deleteAllByIdIn(Set<Long> ids);
}
