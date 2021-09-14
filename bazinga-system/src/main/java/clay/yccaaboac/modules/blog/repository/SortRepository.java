package clay.yccaaboac.modules.blog.repository;

import clay.yccaaboac.modules.blog.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SortRepository extends JpaRepository<Sort,String>, JpaSpecificationExecutor<Sort> {
}
