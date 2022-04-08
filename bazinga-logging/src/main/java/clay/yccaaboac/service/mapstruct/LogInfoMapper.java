package clay.yccaaboac.service.mapstruct;


import clay.yccaaboac.base.BaseMapper;
import clay.yccaaboac.domain.Log;
import clay.yccaaboac.service.dto.LogInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LogInfoMapper extends BaseMapper<LogInfoDto, Log> {
}
