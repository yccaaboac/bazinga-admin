package clay.yccaaboac.modules.blog.domain;

import clay.yccaaboac.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
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

    //如果只有两个@ManyToMany注释而没有mappedBy,则默认将具有两个实体表和两个连接表
    //Blog为控制方,此时BlogDao.update(blog)/save(blog),中间表的数据添加地了
    @ManyToMany
    @ApiModelProperty(value = "标签")
    @JoinTable(name = "blog_blogs_tags",
            joinColumns = {@JoinColumn(name = "blog_id", referencedColumnName = "blog_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "tag_id")})
    private Set<Tag> tags;

    @OneToOne
    @JoinColumn(name = "category_id")
    @ApiModelProperty(value = "分类")
    private Category category;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "简介")
    private String summary;

    @ApiModelProperty(value = "标题图")
    private String titlePicture;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "文章出处")
    private String articleSource;

    @ApiModelProperty(value = "是否原创")
    private String isOriginal;

    @ApiModelProperty(value = "发布状态")
    private String isPublish;

    @ApiModelProperty(value = "点击数")
    private Integer clickCount;

//    @ApiModelProperty(value = "点赞数")
//    private Integer likeCount;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Blog blog = (Blog) o;
        return Objects.equals(id, blog.id) &&
                Objects.equals(title, blog.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
