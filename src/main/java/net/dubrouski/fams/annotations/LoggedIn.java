package net.dubrouski.fams.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.inject.Qualifier;

/**
 *
 * @author Martin Jelínek (xjeline5)
 */
@Qualifier
@Retention(RUNTIME)
@Target( {TYPE, METHOD, PARAMETER, FIELD} )
public @interface LoggedIn {}