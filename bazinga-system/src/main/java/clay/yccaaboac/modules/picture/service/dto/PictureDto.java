package clay.yccaaboac.modules.picture.service.dto;

import clay.yccaaboac.base.BaseDTO;
import clay.yccaaboac.modules.picture.domain.PicCategory;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PictureDto extends BaseDTO implements Serializable {
    private String id;

    private String name;

    private PicCategory picCategory;

    private String pictureUrl;
}
