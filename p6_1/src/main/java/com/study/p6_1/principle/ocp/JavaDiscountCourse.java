package com.study.p6_1.principle.ocp;

/**
 * @program: P6
 * @author: Jean
 * @create: 2022-12-06 17:29
 */
public class JavaDiscountCourse extends JavaCourse{

    public JavaDiscountCourse(Integer id, String name, Double price) {
        super(id, name, price);
    }
    public Double getDiscountPrice(){
        return super.getPrice() * 0.61;
    }

    public Double getOriginPrice() {
        return super.getPrice();
    }

    public Double getPrice() {
        return super.getPrice() * 0.6;
    }
}
