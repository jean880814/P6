package com.study.p6_1.principle.ocp;

import lombok.AllArgsConstructor;

/**
 * @program: P6
 * @author: Jean
 * @create: 2022-12-06 17:14
 */
@AllArgsConstructor
public class JavaCourse implements ICourse {
    private Integer id;
    private String name;
    private Double price;

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Double getPrice() {
        return this.price;
    }
}
