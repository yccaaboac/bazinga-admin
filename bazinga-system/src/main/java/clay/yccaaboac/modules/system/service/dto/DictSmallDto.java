package clay.yccaaboac.modules.system.service.dto;

import clay.yccaaboac.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DictSmallDto extends BaseDTO implements Serializable {
    private Long id;

    private String name;
}
