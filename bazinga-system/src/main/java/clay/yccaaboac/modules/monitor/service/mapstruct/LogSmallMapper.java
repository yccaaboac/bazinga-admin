package clay.yccaaboac.modules.monitor.service.mapstruct;

import clay.yccaaboac.base.BaseMapper;
import clay.yccaaboac.modules.monitor.domain.Log;
import clay.yccaaboac.modules.monitor.service.dto.LogSmallDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LogSmallMapper extends BaseMapper<LogSmallDto, Log> {

}