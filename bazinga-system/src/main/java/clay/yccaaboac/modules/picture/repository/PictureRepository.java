package clay.yccaaboac.modules.picture.repository;

import clay.yccaaboac.modules.picture.domain.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Set;

public interface PictureRepository extends JpaRepository<Picture,Long>, JpaSpecificationExecutor<Picture> {
    /**
     * 根据Id删除
     * @param ids /
     */
    void deleteAllByIdIn(Set<Long> ids);
}
