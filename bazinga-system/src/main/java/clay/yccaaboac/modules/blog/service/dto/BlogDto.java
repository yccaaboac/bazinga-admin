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

    private CategoryDto category;

    private String title;

    private String summary;

    private String titlePicture;

    private String author;

    private String articleSource;

    private String isOriginal;

    private String isPublish;

    private Integer clickCount;

}
