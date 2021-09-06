package clay.yccaaboac.modules.blog.service.dto;

import clay.yccaaboac.annotation.Query;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class BlogQueryCriteria implements Serializable {
    @Query(type = Query.Type.EQUAL)
    private String title;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
