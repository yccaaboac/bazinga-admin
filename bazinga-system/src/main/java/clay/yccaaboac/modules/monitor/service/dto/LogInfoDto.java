package clay.yccaaboac.modules.monitor.service.dto;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class LogInfoDto {

    private Long id;

    private String username;

    private String description;

    private String method;

    private String params;

    private String logType;

    private String requestIp;

    private String address;

    private String browser;

    private Long time;

    private byte[] exceptionDetail;

    private Timestamp createTime;
}
