package clay.yccaaboac.modules.news.service;

import clay.yccaaboac.modules.news.service.dto.CommentDto;
import clay.yccaaboac.modules.news.service.dto.CommentQueryCriteria;

import java.util.List;

public interface CommentService {
    List<CommentDto> queryAll(CommentQueryCriteria criteria, Boolean isQuery) throws Exception;
}
