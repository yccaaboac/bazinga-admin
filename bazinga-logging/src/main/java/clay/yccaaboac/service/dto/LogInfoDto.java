package clay.yccaaboac.service.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class LogInfoDto {

    private Long id;

    private String username;

    private String description;

    private String method;

    private String params;

    private String browser;

    private String requestIp;

    private String address;

    private Timestamp createTime;

    private Long time;

    private String logType;

    private byte[] exceptionDetail;
}
