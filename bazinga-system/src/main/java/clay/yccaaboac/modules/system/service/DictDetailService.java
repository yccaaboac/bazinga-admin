package clay.yccaaboac.modules.system.service;


import clay.yccaaboac.modules.system.service.dto.DictDetailDto;

import java.util.List;

public interface DictDetailService {
    List<DictDetailDto> getDictDetailByName(String name);
}
