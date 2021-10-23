package clay.yccaaboac.modules.picture.service.mapstruct;

import clay.yccaaboac.base.BaseMapper;
import clay.yccaaboac.modules.picture.domain.Picture;
import clay.yccaaboac.modules.picture.service.dto.PictureDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PictureMapper extends BaseMapper<PictureDto, Picture> {
}
