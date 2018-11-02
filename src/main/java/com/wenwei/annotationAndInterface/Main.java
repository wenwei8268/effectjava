package com.wenwei.annotationAndInterface;
/**
 * Created by wenweizww on 2018/9/5.
 */


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * author:zhou_wenwei
 * mail:zhou_wenwei@wuxiapptec.com
 * date:2018/9/5
 * description:
 */
public class Main {

    public static void main(String[] args) throws Exception{
        int tests = 0 ;
        int passed = 0 ;
        Class<?> testClass = Class.forName(new AnnotationTest().getClass().getName());
//        Sample sample = new Sample();
        for(Method method : testClass.getDeclaredMethods()){
            if (method.isAnnotationPresent(ExceptionTest.class)){
                tests ++;
                try {
                    method.invoke(null);
                    System.out.printf("Test %s failed : no exception %n", method);
                }catch (InvocationTargetException wrapped){
                    Throwable exc = wrapped.getCause();
                    Class<? extends Throwable> excType = method.getAnnotation(ExceptionTest.class).value();
                    if(excType.isInstance(exc)){
                        passed++;
                    }else {
                        System.out.printf("Test %s failed:execpted %s ,got %s%n",method,excType.getName(),exc);
                    }

                }catch (Exception exc){
                    System.out.println("Invalid @Test:"+method);
                }

            }
        }
//
//         int i = -1<<29 ;
//        for ( i ++ ; i++ <10 ;++i){
////            if(i%3==1){
////                result +=i;
////            }
//        }
//        System.out.println(i);
    }
}
