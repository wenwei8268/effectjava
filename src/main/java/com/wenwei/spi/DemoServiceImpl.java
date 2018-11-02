package com.wenwei.spi;
/**
 * Created by wenweizww on 2018/10/31.
 */


import com.wenwei.DemoService;

/**
 * author:zhou_wenwei
 * mail:zhou_wenwei@wuxiapptec.com
 * date:2018/10/31
 * description:
 */
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHi(String str) {
        return "hi" + str;
    }
}

