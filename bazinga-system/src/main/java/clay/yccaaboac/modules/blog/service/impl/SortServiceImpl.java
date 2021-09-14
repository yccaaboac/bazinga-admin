package clay.yccaaboac.modules.blog.service.impl;



import clay.yccaaboac.modules.blog.repository.SortRepository;
import clay.yccaaboac.modules.blog.service.SortService;
import clay.yccaaboac.modules.blog.service.dto.SortDto;
import clay.yccaaboac.modules.blog.service.dto.SortQueryCriteria;
import clay.yccaaboac.modules.blog.service.mapstruct.SortMapper;
import clay.yccaaboac.utils.QueryHelp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SortServiceImpl implements SortService {
    private final SortMapper sortMapper;
    private final SortRepository sortRepository;

    @Override
    public List<SortDto> queryAll(SortQueryCriteria criteria, Boolean isQuery) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        List<SortDto> list = sortMapper.toDto(sortRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),sort));
        return list;
    }
}
