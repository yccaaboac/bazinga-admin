package clay.yccaaboac.modules.system.service.mapstruct;

import clay.yccaaboac.base.BaseMapper;
import clay.yccaaboac.modules.system.domain.DictDetail;
import clay.yccaaboac.modules.system.service.dto.DictDetailDto;
import clay.yccaaboac.modules.system.service.dto.DictSmallDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",uses = {DictSmallDto.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DictDetailMapper extends BaseMapper<DictDetailDto, DictDetail> {
}
