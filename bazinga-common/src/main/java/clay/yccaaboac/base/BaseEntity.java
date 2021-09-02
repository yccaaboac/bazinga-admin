package clay.yccaaboac.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Timestamp;

@Getter
@Setter
@MappedSuperclass//该实体类当成基类实体，它不会隐射到数据库表，但继承它的子类实体在隐射时会自动扫描该基类实体的隐射属性，添加到子类实体的对应数据库表中
@EntityListeners(AuditingEntityListener.class)//用于监听实体类添加或者删除操作的，可用于@Entity和@MappedSuperclass
public class BaseEntity implements Serializable {
    @CreatedBy
    @Column(name = "create_by", updatable = false)
    @ApiModelProperty(value = "创建人",hidden = true)
    private String createBy;

    @LastModifiedBy
    @Column(name = "update_by")
    @ApiModelProperty(value = "更新人", hidden = true)
    private String updatedBy;

    @CreationTimestamp
    @Column(name = "create_time", updatable = false)
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Timestamp createTime;


    @UpdateTimestamp
    @Column(name = "update_time")
    @ApiModelProperty(value = "更新时间", hidden = true)
    private Timestamp updateTime;

    /* 分组校验 */
    public @interface Create{}

    /* 分组校验 */
    public @interface Update{}

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        Field[] files = this.getClass().getDeclaredFields();//获得某个类的所有声明的字段，即包括public、private和proteced，但是不包括父类的申明字段
        try {
            for(Field f :files){
                //在Java中可以通过反射进行获取实体类中的字段值，当未设置Field的setAccessible方法为true时，会在调用的时候进行访问安全检查，会抛出IllegalAccessException异常
                f.setAccessible(true);
                builder.append(f.getName(),f.get(this)).append("\n");
            }
        }catch (Exception e) {
            builder.append("toString builder encounter an error");
        }
        return builder.toString();
    }
}
