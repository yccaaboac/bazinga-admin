package clay.yccaaboac.modules.picture.service.impl;

import clay.yccaaboac.exception.EntityExistException;
import clay.yccaaboac.modules.blog.domain.Category;
import clay.yccaaboac.modules.picture.domain.PicCategory;
import clay.yccaaboac.modules.picture.repository.PicCategoryRepository;
import clay.yccaaboac.modules.picture.service.PicCategoryService;
import clay.yccaaboac.modules.picture.service.dto.PicCategoryDto;
import clay.yccaaboac.modules.picture.service.dto.PicCategoryQueryCriteria;
import clay.yccaaboac.modules.picture.service.mapstruct.PicCategoryMapper;
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
public class PicCategoryServiceImpl implements PicCategoryService {
    private final PicCategoryMapper picCategoryMapper;
    private final PicCategoryRepository picCategoryRepository;

    @Override
    public List<PicCategoryDto> queryAll() {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        return picCategoryMapper.toDto(picCategoryRepository.findAll(sort));
    }

    @Override
    public Object queryAll(PicCategoryQueryCriteria criteria, Pageable pageable) {
        Page<PicCategory> page = picCategoryRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(picCategoryMapper::toDto));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(PicCategory category) {
        if (picCategoryRepository.findByName(category.getName()) != null) {
            throw new EntityExistException(Category.class, "name", category.getName());
        }
        picCategoryRepository.save(category);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(PicCategory resources) {
        PicCategory picCategory = picCategoryRepository.findById(resources.getId()).orElseGet(PicCategory::new);
        ValidationUtil.isNull(picCategory.getId(), "Category", "id", resources.getId());
        PicCategory picCategory1 = picCategoryRepository.findByName(resources.getName());
        if (picCategory1 != null && !picCategory.getId().equals(picCategory1.getId())) {
            throw new EntityExistException(Category.class, "name", resources.getName());
        }
        picCategory.setName(resources.getName());
        picCategory.setDescription(resources.getDescription());
        picCategory.setEnabled(resources.getEnabled());
        picCategoryRepository.save(picCategory);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Set<Long> ids) {
        picCategoryRepository.deleteAllByIdIn(ids);
    }
}
