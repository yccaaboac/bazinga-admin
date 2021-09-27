package clay.yccaaboac.modules.blog.service.impl;


import clay.yccaaboac.exception.EntityExistException;
import clay.yccaaboac.modules.blog.domain.Tag;
import clay.yccaaboac.modules.blog.repository.TagRepository;
import clay.yccaaboac.modules.blog.service.TagService;
import clay.yccaaboac.modules.blog.service.dto.TagDto;
import clay.yccaaboac.modules.blog.service.dto.TagQueryCriteria;
import clay.yccaaboac.modules.blog.service.mapstruct.TagMapper;
import clay.yccaaboac.utils.PageUtil;
import clay.yccaaboac.utils.QueryHelp;
import clay.yccaaboac.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

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

    @Override
    public Object queryAll(TagQueryCriteria criteria, Pageable pageable) {
        Page<Tag> page = tagRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(tagMapper::toDto));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Tag tag) {
        if (tagRepository.findByName(tag.getName()) != null) {
            throw new EntityExistException(Tag.class, "name", tag.getName());
        }
        tagRepository.save(tag);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Tag resources) {
        Tag tag = tagRepository.findById(resources.getId()).orElseGet(Tag::new);
        ValidationUtil.isNull(tag.getId(), "Tag", "id", resources.getId());
        Tag tag1 = tagRepository.findByName(resources.getName());
        if (tag1 != null && !tag.getId().equals(tag1.getId())) {
            throw new EntityExistException(Tag.class, "name", resources.getName());
        }
        tag.setName(resources.getName());
        tag.setDescription(resources.getDescription());
        tag.setEnabled(resources.getEnabled());
        tagRepository.save(tag);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Set<Long> ids) {
        tagRepository.deleteAllByIdIn(ids);
    }
}
