package com.study.p6_1.decorate_pattern;

import java.util.List;

public abstract class NavBarDecorate implements NavBar {
    private NavBar navBar;
    public NavBarDecorate(NavBar navBar){
        this.navBar = navBar;
    }
    @Override
    public List<String> getMenu() {
        return navBar.getMenu();
    }
}
