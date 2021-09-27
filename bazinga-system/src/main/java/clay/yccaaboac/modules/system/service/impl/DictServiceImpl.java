package clay.yccaaboac.modules.system.service.impl;

import clay.yccaaboac.modules.system.domain.Dict;
import clay.yccaaboac.modules.system.repository.DictRepository;
import clay.yccaaboac.modules.system.service.DictService;
import clay.yccaaboac.modules.system.service.dto.DictDto;
import clay.yccaaboac.modules.system.service.dto.DictQueryCriteria;
import clay.yccaaboac.modules.system.service.mapstruct.DictMapper;
import clay.yccaaboac.utils.PageUtil;
import clay.yccaaboac.utils.QueryHelp;
import clay.yccaaboac.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DictServiceImpl implements DictService {
    private final DictRepository dictRepository;
    private final DictMapper dictMapper;

    @Override
    public Map<String, Object> queryAll(DictQueryCriteria criteria, Pageable pageable) {
        Page<Dict> page = dictRepository.findAll((root, query, cb) -> QueryHelp.getPredicate(root, criteria, cb), pageable);
        return PageUtil.toPage(page.map(dictMapper::toDto));
    }

    @Override
    public List<DictDto> queryAll(DictQueryCriteria dict) {
        List<Dict> list = dictRepository.findAll((root, query, cb) -> QueryHelp.getPredicate(root, dict, cb));
        return dictMapper.toDto(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Dict resources) {
        dictRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Dict resources) {
        Dict dict = dictRepository.findById(resources.getId()).orElseGet(Dict::new);
        ValidationUtil.isNull( dict.getId(),"Dict","id",resources.getId());
        resources.setId(dict.getId());
        dictRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Set<Long> ids) {
        dictRepository.deleteByIdIn(ids);
    }
}
