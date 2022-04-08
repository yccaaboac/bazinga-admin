package clay.yccaaboac.modules.news.domain;

import clay.yccaaboac.base.BaseEntity;
import clay.yccaaboac.modules.blog.domain.Blog;
import clay.yccaaboac.modules.system.domain.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "news_comment")
public class Comment extends BaseEntity implements Serializable {

    @Id
    @Column(name = "comment_id")
    @NotNull(groups = Update.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "ID", hidden = true)
    private Long id;

    /**
     * 该评论来源的博客
     */
    @ManyToOne
    @JoinColumn(name = "blog_id")
    @ApiModelProperty(value = "博客")
    private Blog blog;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 本条评论是哪个用户说的
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    @ApiModelProperty(value = "本条评论是哪个用户说的")
    private User user;


    /**
     * 本条评论回复的那条评论
     */
    @ManyToOne
    @JoinColumn(name = "pid")
    @ApiModelProperty(value = "本条评论回复的那条评论")
    private Comment parentComment;

    /**
     * 本条评论的一级评论
     */
    @ManyToOne
    @JoinColumn(name = "first_comment_id")
    @ApiModelProperty(value = "本条评论的一级评论")
    private Comment firstComment;

//    @ApiModelProperty(value = "本条评论回复的那条评论")
//    private Long pid;


}
