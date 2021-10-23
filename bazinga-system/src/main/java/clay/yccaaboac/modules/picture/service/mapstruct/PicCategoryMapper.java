package clay.yccaaboac.modules.picture.service.mapstruct;

import clay.yccaaboac.base.BaseMapper;
import clay.yccaaboac.modules.picture.domain.PicCategory;
import clay.yccaaboac.modules.picture.service.dto.PicCategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PicCategoryMapper extends BaseMapper<PicCategoryDto, PicCategory> {
}