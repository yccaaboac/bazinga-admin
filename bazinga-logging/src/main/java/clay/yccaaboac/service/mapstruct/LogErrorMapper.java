package clay.yccaaboac.service.mapstruct;


import clay.yccaaboac.base.BaseMapper;
import clay.yccaaboac.domain.Log;
import clay.yccaaboac.service.dto.LogErrorDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LogErrorMapper extends BaseMapper<LogErrorDto, Log> {

}