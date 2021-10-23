package clay.yccaaboac.modules.picture.service.dto;

import clay.yccaaboac.annotation.Query;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class PicCategoryQueryCriteria {
    @Query(blurry = "name,description")
    private String blurry;

    @Query
    private Boolean enabled;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
