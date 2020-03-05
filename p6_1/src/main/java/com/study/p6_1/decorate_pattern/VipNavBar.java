package com.study.p6_1.decorate_pattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VipNavBar extends NavBarDecorate {
    public VipNavBar(NavBar navBar) {
        super(navBar);
    }

    @Override
    public List<String> getMenu() {
        List<String> vipMenu = new ArrayList<>(super.getMenu());
        vipMenu.addAll(Arrays.asList(new String[]{"作业","题库","成长墙"}));
        return vipMenu;
    }
}
