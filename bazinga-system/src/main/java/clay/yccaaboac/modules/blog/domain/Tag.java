package clay.yccaaboac.modules.blog.domain;

import clay.yccaaboac.base.BaseEntity;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "blog_tag")
public class Tag extends BaseEntity implements Serializable {
    @Id
    @Column(name = "tag_id")
    @ApiModelProperty(value = "ID", hidden = true)
    @NotNull(groups = Update.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @ApiModelProperty(value = "标签名称")
    private String name;

    @ApiModelProperty(value = "标签描述")
    private String description;

    @JSONField(serialize = false)
    //如果只有两个@ManyToMany注释而没有mappedBy,则默认将具有两个实体表和两个连接表
    //Tag为被控制方,此时tagDao.update(tag)/save(tag),中间表的数据添加不了
    @ManyToMany(mappedBy = "tags")
    @ApiModelProperty(value = "博客")
    private Set<Blog> blogs;


}
