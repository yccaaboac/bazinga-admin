package clay.yccaaboac.modules.blog.service.dto;

import clay.yccaaboac.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;


@Getter
@Setter
public class BlogDto extends BaseDTO implements Serializable {
    private Long id;

    private Set<TagDto> tags;

    private SortDto sort;

    private String title;

    private String titlePicture;

    private String author;

    private Boolean isOriginal;

    private Boolean isPublish;

    private Integer clickCount;

}
