package clay.yccaaboac.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class RsaProperties {

    public static String privateKey;

/*在处理静态变量时候，使用
        @Value
        public static String privateKey;
    的用法是无法获取到配置文件中的数据的，获取到null，所以要进行如下更改
    利用IDEA生成该静态变量的set方法
        public static void setPrivateKey(String privateKey) {
            RsaProperties.privateKey = privateKey;
        }
    然后删除该方法的static修饰，然后将注解@Value写在set函数上面，这样就可以正常读取到配置文件中的信息
    setPrivateKey执行的非常早，在系统尚未正式启动之前就已经执行了，应该是在spring初始化bean内容时执行的*/
    @Value("${rsa.private_key}")
    public void setPrivateKey(String privateKey) {
        RsaProperties.privateKey = privateKey;
    }
}