package clay.yccaaboac.modules.system.repository;


import clay.yccaaboac.modules.system.domain.Dict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Set;

public interface DictRepository extends JpaRepository<Dict,Long>, JpaSpecificationExecutor<Dict> {
    /**
     * 删除
     * @param ids /
     */
    void deleteByIdIn(Set<Long> ids);
}
