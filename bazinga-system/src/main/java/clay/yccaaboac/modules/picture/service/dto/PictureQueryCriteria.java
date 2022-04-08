package clay.yccaaboac.modules.picture.service.dto;

import clay.yccaaboac.annotation.Query;
import lombok.Data;

@Data
public class PictureQueryCriteria {

    @Query(blurry = "name")
    private String blurry;

    @Query(propName = "name",joinName = "picCategory")
    private String categoryName;
}
