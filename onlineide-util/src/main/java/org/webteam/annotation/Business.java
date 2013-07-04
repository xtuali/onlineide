package org.webteam.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * Indicates that an annotated class is a "Service" (e.g. a business service
 * facade).
 * 
 * <p>
 * This annotation serves as a specialization of {@link Component @Component},
 * allowing for implementation classes to be autodetected through classpath
 * scanning.
 * 
 * @author Juergen Hoeller
 * @since 2.5
 * @see Component
 * @see org.springframework.context.annotation.ClassPathBeanDefinitionScanner
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Business {

	/**
	 * The value may indicate a suggestion for a logical component name, to be
	 * turned into a Spring bean in case of an autodetected component.
	 * 
	 * @return the suggested component name, if any
	 */
	String value() default "";

}
