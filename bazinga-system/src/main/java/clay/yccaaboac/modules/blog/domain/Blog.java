package clay.yccaaboac.modules.blog.domain;

import clay.yccaaboac.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;


@Entity
@Getter
@Setter
@Table(name = "blog_blog")
public class Blog extends BaseEntity implements Serializable {

    @Id
    @Column(name = "blog_id")
    @NotNull(groups = Update.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "ID", hidden = true)
    private Long id;


    @ManyToMany
    @ApiModelProperty(value = "标签")
    @JoinTable(name = "blog_blogs_tags",
            joinColumns = {@JoinColumn(name = "blog_id", referencedColumnName = "blog_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "tag_id")})
    private Set<Tag> tags;


    @OneToOne
    @JoinColumn(name = "sort_id")
    @ApiModelProperty(value = "分类")
    private Sort sort;


    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "标题图")
    private String titlePicture;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "是否原创")
    private Boolean isOriginal;

    @ApiModelProperty(value = "发布状态")
    private Boolean isPublish;

    @ApiModelProperty(value = "点击数")
    private Integer clickCount;

//    @ApiModelProperty(value = "点赞数")
//    private Integer likeCount;

}
