package com.wenwei.serialization.defaultJdk;
/**
 * Created by wenweizww on 2018/11/2.
 */


import java.io.Serializable;
import java.util.Date;

/**
 * author:zhou_wenwei
 * mail:zhou_wenwei@wuxiapptec.com
 * date:2018/11/2
 * description:
 */
public class User implements Serializable{
    private String jvmId;
    private Integer number ;
    private Date date ;
//    private Date


    private static final long serialVersionUID = 682565338379275953L;


    public String getJvmId() {
        return jvmId;
    }

    public void setJvmId(String jvmId) {
        this.jvmId = jvmId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "User{" +
                "jvmId='" + jvmId + '\'' +
                ", number=" + number +
                ", date=" + date +
                '}';
    }
}
