package clay.yccaaboac.modules.system.service.dto;

import clay.yccaaboac.annotation.Query;
import lombok.Data;

@Data
public class DictQueryCriteria {
    @Query(blurry = "name,description")
    private String blurry;
}
