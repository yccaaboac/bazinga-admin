package clay.yccaaboac.modules.news.service.dto;

import clay.yccaaboac.annotation.Query;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class CommentQueryCriteria {

    @Query(blurry = "content")
    private String blurry;

    @Query(type = Query.Type.IS_NULL, propName = "id", joinName = "parentComment")
    private Boolean pidIsNull;

//    @Query(type = Query.Type.IS_NULL, propName = "content",joinName = "comment")
//    private Boolean pidIsNull;

    @Query(propName = "id", joinName = "blog")
    private Long blogId;

    @Query(propName = "id", joinName = "parentComment")
    private Long parentCommentId;

    @Query(propName = "id", joinName = "firstComment")
    private Long firstCommentId;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;


}