package clay.yccaaboac.modules;

import clay.yccaaboac.modules.blog.repository.BlogRepository;
import clay.yccaaboac.modules.news.repository.CommentRepository;
import clay.yccaaboac.modules.news.rest.CommentController;
import clay.yccaaboac.modules.news.service.CommentService;
import clay.yccaaboac.modules.news.service.dto.CommentDto;
import clay.yccaaboac.modules.news.service.dto.CommentQueryCriteria;
import clay.yccaaboac.modules.system.repository.MenuRepository;
import clay.yccaaboac.modules.system.repository.UserRepository;
import clay.yccaaboac.modules.system.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AppTest {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    MenuRepository menuRepository;
    @Autowired
    CommentController commentController;
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BlogRepository blogRepository;

    @Test
    public void test1() throws Exception {
//        CommentQueryCriteria commentQueryCriteria  = new CommentQueryCriteria();
////        commentQueryCriteria.setBlogId(2L);
//        List<CommentDto> commentDtos = commentService.queryAll(commentQueryCriteria, true);
//        List<Comment> all = commentRepository.findAll();
//        System.out.println(all);
//        menuService.queryAll(criteria, true);
//        List<Menu> all = menuRepository.findAll();
//        System.out.println(all);
//        UserDto byId = userService.findById(1);
//        Optional<Blog> byId = blogRepository.findById(1L);
//        System.out.println(byId);

//        List<Comment> all = commentRepository.findAll();
//        System.out.println(all);
//        CommentQueryCriteria commentQueryCriteria  = new CommentQueryCriteria();
//        commentQueryCriteria.setBlogId(19L);
//        List<CommentDto> commentDtos = commentService.queryAll(commentQueryCriteria, false);
//        System.out.println(commentDtos);
//        List<Comment> all = commentRepository.findAll();
//        System.out.println(all);
        String a = "aaa";
        System.out.println(a=="aaa");

    }

    @Test
    public void test2() throws Exception {
        CommentQueryCriteria commentQueryCriteria  = new CommentQueryCriteria();
        List<CommentDto> commentDtos = commentService.queryAll(commentQueryCriteria, false);
        System.out.println(commentDtos);
    }
}
