package clay.yccaaboac.annotation;

import java.lang.annotation.*;

/**
 *  用于标记匿名访问方法
 */
//如果一个类用上了@Inherited修饰的注解，那么其子类也会继承这个注解
    //接口用上个@Inherited修饰的注解，其实现类不会继承这个注解
    //父类的方法用了@Inherited修饰的注解，子类也不会继承这个注解
    //当用了@Inherited修饰的注解的@Retention是RetentionPolicy.RUNTIME，则增强了继承性，在反射中可以获取得到
//只有当父类的注解中用@Inherited修饰，子类的getAnnotations()才能获取得到父亲的注解以及自身的注解，而getDeclaredAnnotations()只会获取自身的注解，无论如何都不会获取父亲的注解
@Inherited
@Documented//在生成javadoc的时候就会把@Documented注解给显示出来
@Target({ElementType.METHOD,ElementType.ANNOTATION_TYPE})//METHOD:用于描述方法，TYPE:用于描述类、接口(包括注解类型) 或enum声明，ANNOTATION_TYPE:用于描述包括注解类型
@Retention(RetentionPolicy.RUNTIME)//注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在
public @interface AnonymousAccess {

}
