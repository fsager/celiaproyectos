package ar.com.celia.common.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({METHOD, FIELD}) 
@Retention(RUNTIME)
public @interface Domain {
	public String domainClass();
	public String domainValue();
	public String attributeDominio() default "domDominio";
	public String attributeMostrar() default "domTexto";
	public String attributeClave() default "domClave";
	public String methodToRetrive() default "getAll";
	public String convertTo() default "java.lang.Long";
}
