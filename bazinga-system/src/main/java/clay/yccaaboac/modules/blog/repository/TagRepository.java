package clay.yccaaboac.modules.blog.repository;


import clay.yccaaboac.modules.blog.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Set;

public interface TagRepository extends JpaRepository<Tag,Long>, JpaSpecificationExecutor<Tag> {
    /**
     * 根据标签名称查询
     * @param name
     * @return /
     */
    Tag findByName(String name);

    /**
     * 根据Id删除
  * @param ids /
     */
    void deleteAllByIdIn(Set<Long> ids);
}
