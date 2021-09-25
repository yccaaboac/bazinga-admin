package clay.yccaaboac.modules.blog.repository;

import clay.yccaaboac.modules.blog.domain.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Set;

public interface BlogRepository extends JpaRepository<Blog,Long>, JpaSpecificationExecutor<Blog> {
    /**
     * 根据博客标题查询
     * @param title
     * @return /
     */
    Blog findByTitle(String title);

    /**
     * 根据Id删除
     * @param ids /
     */
    void deleteAllByIdIn(Set<Long> ids);
}
