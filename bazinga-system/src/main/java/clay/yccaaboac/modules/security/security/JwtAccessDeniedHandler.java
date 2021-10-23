package clay.yccaaboac.modules.security.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

   @Override
   public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
      //当用户在没有授权的情况下访问受保护的REST资源时，将调用此方法发送403 Forbidden响应
      /*{
         "timestamp": "2021-10-23 02:25:20",
              "status": 403,
              "error": "Forbidden",
              "message": "不允许访问",
              "path": "/api/picCategories"
      }*/
      response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
   }
}
