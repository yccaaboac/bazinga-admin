package clay.yccaaboac.modules.blog.repository;

import clay.yccaaboac.modules.blog.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CategoryRepository extends JpaRepository<Category,String>, JpaSpecificationExecutor<Category> {
}
