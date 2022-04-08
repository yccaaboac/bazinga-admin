package clay.yccaaboac.modules.blog.service.dto;

import clay.yccaaboac.annotation.Query;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Data
public class BlogQueryCriteria implements Serializable {
    @Query(blurry = "title")
    private String title;

    @Query
    private String isPublish;

    @Query
    private String isOriginal;

    @Query(propName = "id", joinName = "category")
    private Long categoryId;

//    @Query(propName = "id", type = Query.Type.IN, joinName = "tags")
//    private Set<Long> tagIds;


    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;

    @Query
    private String openComment;
}
