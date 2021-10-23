package clay.yccaaboac.modules.picture.domain;

import clay.yccaaboac.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@Getter
@Setter
@Table(name = "picture_picture")
public class Picture extends BaseEntity implements Serializable {
    @Id
    @Column(name = "picture_id")
    @NotNull(groups = Update.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "ID", hidden = true)
    private String id;

    @ApiModelProperty(value = "图片名称")
    private String name;

    @OneToOne
    @JoinColumn(name = "category_id")
    @ApiModelProperty(value = "图片分类")
    private PicCategory picCategory;

    @ApiModelProperty(value = "图片路径")
    private String pictureUrl;

}