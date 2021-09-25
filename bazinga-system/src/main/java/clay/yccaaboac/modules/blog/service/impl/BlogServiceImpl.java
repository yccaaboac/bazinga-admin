package clay.yccaaboac.modules.blog.service.impl;

import clay.yccaaboac.exception.EntityExistException;
import clay.yccaaboac.modules.blog.domain.Blog;
import clay.yccaaboac.modules.blog.repository.BlogRepository;
import clay.yccaaboac.modules.blog.service.BlogService;
import clay.yccaaboac.modules.blog.service.dto.BlogQueryCriteria;
import clay.yccaaboac.modules.blog.service.mapstruct.BlogMapper;
import clay.yccaaboac.utils.PageUtil;
import clay.yccaaboac.utils.QueryHelp;
import clay.yccaaboac.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Blog blog) {
        if (blogRepository.findByTitle(blog.getTitle()) != null) {
            throw new EntityExistException(Blog.class, "title", blog.getTitle());
        }
        blogRepository.save(blog);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Blog resources) {
        Blog blog = blogRepository.findById(resources.getId()).orElseGet(Blog::new);
        ValidationUtil.isNull(blog.getId(), "Blog", "id", resources.getId());
        Blog blog1 = blogRepository.findByTitle(resources.getTitle());
        if (blog1 != null && !blog.getId().equals(blog1.getId())) {
            throw new EntityExistException(Blog.class, "title", resources.getTitle());
        }
        blog.setArticleSource(resources.getArticleSource());
        blog.setAuthor(resources.getAuthor());
        blog.setCategory(resources.getCategory());
        blog.setIsOriginal(resources.getIsOriginal());
        blog.setIsPublish(resources.getIsPublish());
        blog.setSummary(resources.getSummary());
        blog.setTitle(resources.getTitle());
        blog.setTitlePicture(resources.getTitlePicture());
        blog.setTags(resources.getTags());
        blogRepository.save(blog);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Set<Long> ids) {
        blogRepository.deleteAllByIdIn(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changeRelease(Long id, String isPublish) {
        Blog blog = blogRepository.findById(id).orElseGet(Blog::new);
        ValidationUtil.isNull(blog.getId(),"Blog","id",id);
        blog.setIsPublish(isPublish);
        blogRepository.save(blog);
    }
}
