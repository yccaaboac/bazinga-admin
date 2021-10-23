package clay.yccaaboac.modules.picture.repository;

import clay.yccaaboac.modules.picture.domain.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PictureRepository extends JpaRepository<Picture,Long>, JpaSpecificationExecutor<Picture> {
}
