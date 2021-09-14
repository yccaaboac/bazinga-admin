package clay.yccaaboac.modules.blog.service.mapstruct;

import clay.yccaaboac.base.BaseMapper;
import clay.yccaaboac.modules.blog.domain.Tag;
import clay.yccaaboac.modules.blog.service.dto.TagDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper extends BaseMapper<TagDto, Tag> {
}
