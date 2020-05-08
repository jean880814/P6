package com.study.p6_2.mybatis.Jbatis.mapper;


import com.study.p6_2.mybatis.Jbatis.annotation.Entity;
import com.study.p6_2.mybatis.Jbatis.annotation.JSelect;

@Entity(Blog.class)
public interface BlogMapper {
    /**
     * 根据主键查询文章
     * @param bid
     * @return
     */
    @JSelect("select * from blog where bid = ?")
    public Blog selectBlogById(Integer bid);

}
