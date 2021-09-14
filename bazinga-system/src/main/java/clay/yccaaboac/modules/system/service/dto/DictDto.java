package clay.yccaaboac.modules.system.service.dto;

import clay.yccaaboac.base.BaseDTO;
import clay.yccaaboac.modules.system.domain.DictDetail;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


@Getter
@Setter
public class DictDto extends BaseDTO implements Serializable {
    private Long id;

    private List<DictDetail> dictDetails;

    private String name;

    private String description;
}
