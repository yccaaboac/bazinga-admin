package clay.yccaaboac.modules.blog.service.dto;

import clay.yccaaboac.annotation.Query;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Data
public class CategoryQueryCriteria implements Serializable {
    @Query(blurry = "name,description")
    private String blurry;

    @Query
    private Boolean enabled;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
