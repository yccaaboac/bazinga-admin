package clay.yccaaboac.modules.blog.service.dto;

import clay.yccaaboac.base.BaseDTO;
import clay.yccaaboac.base.BaseEntity;
import clay.yccaaboac.modules.blog.domain.Sort;
import clay.yccaaboac.modules.blog.domain.Tag;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;


@Getter
@Setter
public class BlogDto extends BaseDTO implements Serializable {
    private Long id;

    private Set<TagDto> tags;

    private Sort sort;

    private String title;

    private String titlePicture;

    private String author;

    private Boolean isOriginal;

    private Boolean isPublish;

    private Integer clickCount;

}
