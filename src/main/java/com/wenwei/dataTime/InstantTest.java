package com.wenwei.dataTime;
/**
 * Created by wenweizww on 2018/9/16.
 */


import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
import java.util.Locale;

/**
 * author:zhou_wenwei
 * mail:zhou_wenwei@wuxiapptec.com
 * date:2018/9/16
 * description:
 */
public class InstantTest {
//    @Test
//    public void testInstant(){
//        Clock clock = Clock.system(ZoneId.of("America/Argentina/Buenos_Aires"));
//
//
//        Instant instant = Instant.now();
//        System.out.println(instant.getNano());//相对于1970 01-01
//
////        DateTime dateTime
//
//        LocalTime time = LocalTime.now(clock);
//        System.out.println(time);
//
//        LocalDate localDateTime = LocalDate.now();
//        System.out.println(localDateTime.getChronology()+":"+time.getMinute());
//
//         LocalDate localDate = LocalDate.now(clock);
//        System.out.println(localDate.getDayOfMonth()+","+localDate.getDayOfWeek()+","+localDate.getDayOfYear());
//    }
    @Test
    public void testDate(){
         SimpleDateFormat sdf_en = new SimpleDateFormat(" HH:mm:ss", Locale.ENGLISH);
        Date dt = Date.from(Instant.now());

        System.out.println(sdf_en.format(dt) );

    }
}
