package com.wenwei.annotationAndInterface;
/**
 * Created by wenweizww on 2018/9/5.
 */


import java.lang.annotation.*;

/**
 * author:zhou_wenwei
 * mail:zhou_wenwei@wuxiapptec.com
 * date:2018/9/5
 * description:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExceptionTest {
    Class<? extends Exception> value();
}
