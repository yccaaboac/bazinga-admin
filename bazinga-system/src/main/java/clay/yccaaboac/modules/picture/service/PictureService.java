package clay.yccaaboac.modules.picture.service;

import clay.yccaaboac.modules.picture.domain.Picture;
import clay.yccaaboac.modules.picture.service.dto.PictureQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface PictureService {

    Object queryAll(PictureQueryCriteria criteria, Pageable pageable);

    void delete(Set<Long> ids);

    void create(Picture resources);

    void update(Picture picture);
}
