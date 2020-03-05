package com.study.p6_1.decorate_pattern;

import java.util.Arrays;
import java.util.List;

public class BaseNavBar implements NavBar {
    @Override
    public List<String> getMenu() {
        return Arrays.asList(new String[]{"问答","文章","精品课","冒泡","商城"});
    }
}
