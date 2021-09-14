package clay.yccaaboac.modules.system.service.impl;

import clay.yccaaboac.modules.system.repository.DictDetailRepository;
import clay.yccaaboac.modules.system.service.DictDetailService;
import clay.yccaaboac.modules.system.service.dto.DictDetailDto;
import clay.yccaaboac.modules.system.service.mapstruct.DictDetailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DictDetailServiceImpl implements DictDetailService {

    private final DictDetailRepository dictDetailRepository;

    private final DictDetailMapper dictDetailMapper;

    @Override
    public List<DictDetailDto> getDictDetailByName(String name) {
        List<DictDetailDto> dictDetailDtoList = dictDetailMapper.toDto(dictDetailRepository.findByDictName(name));
        return dictDetailDtoList;
    }
}
