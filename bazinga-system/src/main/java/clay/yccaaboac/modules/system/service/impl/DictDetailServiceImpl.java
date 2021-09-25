package clay.yccaaboac.modules.system.service.impl;

import clay.yccaaboac.modules.system.domain.DictDetail;
import clay.yccaaboac.modules.system.repository.DictDetailRepository;
import clay.yccaaboac.modules.system.service.DictDetailService;
import clay.yccaaboac.modules.system.service.dto.DictDetailDto;
import clay.yccaaboac.modules.system.service.dto.DictDetailQueryCriteria;
import clay.yccaaboac.modules.system.service.mapstruct.DictDetailMapper;
import clay.yccaaboac.utils.PageUtil;
import clay.yccaaboac.utils.QueryHelp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Override
    public Map<String,Object> queryAll(DictDetailQueryCriteria criteria, Pageable pageable) {
        Page<DictDetail> page = dictDetailRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(dictDetailMapper::toDto));
    }
}
