package clay.yccaaboac.modules.blog.rest;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "博客：标签管理")
@RestController
@RequestMapping("/api/tags")
public class TagController {
}
