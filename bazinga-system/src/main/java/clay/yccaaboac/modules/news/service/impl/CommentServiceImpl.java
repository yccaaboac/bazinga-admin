package clay.yccaaboac.modules.news.service.impl;

import clay.yccaaboac.modules.news.repository.CommentRepository;
import clay.yccaaboac.modules.news.service.CommentService;
import clay.yccaaboac.modules.news.service.dto.CommentDto;
import clay.yccaaboac.modules.news.service.dto.CommentQueryCriteria;
import clay.yccaaboac.modules.news.service.mapstruct.CommentMapper;
import clay.yccaaboac.utils.QueryHelp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;

    @Override
    public List<CommentDto> queryAll(CommentQueryCriteria criteria, Boolean isQuery) throws Exception {
//        if (isQuery) {
//            criteria.setPidIsNull(true);
//            List<Field> fields = QueryHelp.getAllFields(criteria.getClass(), new ArrayList<>());
//            for (Field field : fields) {
//                //设置对象的访问权限，保证对private的属性的访问
//                field.setAccessible(true);
//                Object val = field.get(criteria);
//                if ("pidIsNull".equals(field.getName())) {
//                    continue;
//                }
//                if (ObjectUtil.isNotNull(val)) {
//                    criteria.setPidIsNull(null);
//                    break;
//                }
//            }
//        }
        return commentMapper.toDto(commentRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }
}
