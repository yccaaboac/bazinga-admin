package clay.yccaaboac.modules.blog.service.mapstruct;

import clay.yccaaboac.base.BaseMapper;
import clay.yccaaboac.modules.blog.domain.Sort;
import clay.yccaaboac.modules.blog.service.dto.SortDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SortMapper extends BaseMapper<SortDto, Sort> {
}
