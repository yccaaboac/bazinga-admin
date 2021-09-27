package clay.yccaaboac.modules.blog.service.dto;

import clay.yccaaboac.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TagDto extends BaseDTO implements Serializable {
    private Long id;

    private String name;

    private String description;

    private Boolean enabled;
}
