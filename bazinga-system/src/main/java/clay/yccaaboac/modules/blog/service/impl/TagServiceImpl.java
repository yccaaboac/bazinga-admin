package clay.yccaaboac.modules.blog.service.impl;


import clay.yccaaboac.modules.blog.repository.TagRepository;
import clay.yccaaboac.modules.blog.service.TagService;
import clay.yccaaboac.modules.blog.service.dto.TagDto;
import clay.yccaaboac.modules.blog.service.dto.TagQueryCriteria;
import clay.yccaaboac.modules.blog.service.mapstruct.TagMapper;
import clay.yccaaboac.utils.QueryHelp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagMapper tagMapper;
    private final TagRepository tagRepository;

    @Override
    public List<TagDto> queryAll(TagQueryCriteria criteria, Boolean isQuery) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        List<TagDto> list = tagMapper.toDto(tagRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),sort));
        return list;
    }
}
