package clay.yccaaboac.modules.blog.domain;

import clay.yccaaboac.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "blog_category")
public class Category extends BaseEntity implements Serializable {
    @Id
    @Column(name="category_id")
    @ApiModelProperty(value = "ID",hidden = true)
    @NotNull(groups = Update.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @ApiModelProperty(value = "分类名称")
    private String name;

    @ApiModelProperty(value = "分类描述")
    private String description;

    @NotNull
    @ApiModelProperty(value = "是否启用")
    private Boolean enabled;
}
