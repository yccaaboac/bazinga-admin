package clay.yccaaboac.modules.system.service.mapstruct;

import clay.yccaaboac.base.BaseMapper;
import clay.yccaaboac.modules.system.domain.Dict;
import clay.yccaaboac.modules.system.service.dto.DictDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",uses = {DictDetailMapper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DictMapper extends BaseMapper<DictDto, Dict> {
}
