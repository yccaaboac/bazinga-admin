package clay.yccaaboac.modules.blog.service.impl;


import clay.yccaaboac.modules.blog.domain.Category;
import clay.yccaaboac.modules.blog.repository.CategoryRepository;
import clay.yccaaboac.modules.blog.service.CategoryService;
import clay.yccaaboac.modules.blog.service.dto.CategoryDto;
import clay.yccaaboac.modules.blog.service.dto.CategoryQueryCriteria;
import clay.yccaaboac.modules.blog.service.mapstruct.CategoryMapper;
import clay.yccaaboac.utils.PageUtil;
import clay.yccaaboac.utils.QueryHelp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
