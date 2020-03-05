package com.study.p6_1.decorate_pattern;

import org.springframework.util.StringUtils;

public class NavBarTest {
    public static void main(String[] args) {
        NavBar navBar = new BaseNavBar();
        System.out.println(StringUtils.collectionToCommaDelimitedString(navBar.getMenu()));
        navBar = new VipNavBar(navBar);
        System.out.println(StringUtils.collectionToCommaDelimitedString(navBar.getMenu()));
    }
}
