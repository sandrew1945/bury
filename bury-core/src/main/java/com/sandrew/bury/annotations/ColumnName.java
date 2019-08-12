package com.sandrew.bury.annotations;

import java.lang.annotation.*;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ColumnName
{
	public String value() default "";

	public boolean isPK() default false;

	public boolean autoIncrement() default false;
}
