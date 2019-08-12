package com.sandrew.bury.annotations;

import java.lang.annotation.*;


/**
 * 	表名注解，用来标注类对应的表
 * @author Administrator
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface TableName
{
	 public String value() default "";
}
