package ar.com.celia.common.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({METHOD, FIELD, TYPE}) 
@Retention(RUNTIME)
public @interface ZkComponent {
	public String componentClass() default "";
	public String height() default "";
	public String width() default "";
	public int rows() default -1;
	public int columns() default -1;
	public String format() default "";
	public String constraint() default "";
	public String label() default "";
	public boolean showInListbox() default true;

}
