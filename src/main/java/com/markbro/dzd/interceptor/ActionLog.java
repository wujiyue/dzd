package com.markbro.dzd.interceptor;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ActionLog {
    String description() default "";
    RecordType recordTypes() default RecordType.ALL;
    String[] roleNames() default {};

    enum RecordType{
        ALL,//监控所有用户的行为
        ROLE,//监控角色的行为
        BLACKLIST;//键控黑名单用户的行为,黑名单用户是指用户表的标记为黑名的用户
    }
}
