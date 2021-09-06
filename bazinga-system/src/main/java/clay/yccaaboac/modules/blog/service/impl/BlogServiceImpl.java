package clay.yccaaboac.modules.blog.service.impl;

import clay.yccaaboac.modules.blog.domain.Blog;
import clay.yccaaboac.modules.blog.repository.BlogRepository;
import clay.yccaaboac.modules.blog.service.BlogService;
import clay.yccaaboac.modules.blog.service.dto.BlogQueryCriteria;
import clay.yccaaboac.modules.blog.service.mapstruct.BlogMapper;
import clay.yccaaboac.utils.PageUtil;
import clay.yccaaboac.utils.QueryHelp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {
    private final BlogRepository blogRepository;
    private final BlogMapper blogMapper;

    @Override
    public Object queryAll(BlogQueryCriteria criteria, Pageable pageable) {
        Page<Blog> page = blogRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(blogMapper::toDto));
    }
}
