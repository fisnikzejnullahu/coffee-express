package com.fisnikz.coffee_express.logging;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.*;

/**
 * @author Fisnik Zejnullahu
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@InterceptorBinding
public @interface Logged {
}
