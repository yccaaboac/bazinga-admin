package clay.yccaaboac.modules.blog.service.impl;


import clay.yccaaboac.exception.EntityExistException;
import clay.yccaaboac.modules.blog.domain.Category;
import clay.yccaaboac.modules.blog.repository.CategoryRepository;
import clay.yccaaboac.modules.blog.service.CategoryService;
import clay.yccaaboac.modules.blog.service.dto.CategoryDto;
import clay.yccaaboac.modules.blog.service.dto.CategoryQueryCriteria;
import clay.yccaaboac.modules.blog.service.mapstruct.CategoryMapper;
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
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> queryAll() {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        return categoryMapper.toDto(categoryRepository.findAll(sort));
    }

    @Override
    public Object queryAll(CategoryQueryCriteria criteria, Pageable pageable) {
        Page<Category> page = categoryRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(categoryMapper::toDto));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Category category) {
        if (categoryRepository.findByName(category.getName()) != null) {
            throw new EntityExistException(Category.class, "name", category.getName());
        }
        categoryRepository.save(category);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Category resources) {
        Category category = categoryRepository.findById(resources.getId()).orElseGet(Category::new);
        ValidationUtil.isNull(category.getId(), "Category", "id", resources.getId());
        Category category1 = categoryRepository.findByName(resources.getName());
        if (category1 != null && !category.getId().equals(category1.getId())) {
            throw new EntityExistException(Category.class, "name", resources.getName());
        }
        category.setName(resources.getName());
        category.setDescription(resources.getDescription());
        category.setEnabled(resources.getEnabled());
        categoryRepository.save(category);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Set<Long> ids) {
        categoryRepository.deleteAllByIdIn(ids);
    }
}
