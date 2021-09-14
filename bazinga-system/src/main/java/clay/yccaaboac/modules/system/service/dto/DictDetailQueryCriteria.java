package clay.yccaaboac.modules.system.service.dto;

import clay.yccaaboac.annotation.Query;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class DictDetailQueryCriteria {
    @Query(propName = "name", type = Query.Type.IN, joinName = "dict")
    private Set<String> names = new HashSet<>();
}
