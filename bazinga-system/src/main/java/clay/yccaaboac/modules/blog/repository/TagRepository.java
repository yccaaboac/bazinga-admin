package clay.yccaaboac.modules.blog.repository;


import clay.yccaaboac.modules.blog.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TagRepository extends JpaRepository<Tag,String>, JpaSpecificationExecutor<Tag> {
}
