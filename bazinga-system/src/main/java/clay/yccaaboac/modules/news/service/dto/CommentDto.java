package clay.yccaaboac.modules.news.service.dto;

import clay.yccaaboac.base.BaseDTO;
import clay.yccaaboac.modules.blog.service.dto.BlogDto;
import clay.yccaaboac.modules.news.domain.Comment;
import clay.yccaaboac.modules.system.service.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class CommentDto extends BaseDTO implements Serializable {

    private Long id;

    private BlogDto blog;

    private String content;

    private UserDto user;

    private Comment parentComment;

    private Comment firstComment;

    private Long pid;

    public Boolean getHasChildren() {
        return firstComment == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CommentDto commentDto = (CommentDto) o;
        return Objects.equals(id, commentDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
