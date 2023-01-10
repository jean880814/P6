package com.study.p6_1.principle.lsp;

/**
 * @program: P6
 * @author: Jean
 * @create: 2023-01-09 14:45
 */
public class Test {
    public static void resize(Rectangle rectangle) {
        while(rectangle.getWidth() >= rectangle.getHeight()){
            rectangle.setHeight(rectangle.getHeight() + 1);
            System.out.println("Width:" + rectangle.getWidth() + ",Height:" + rectangle.getHeight());
        }
        System.out.println("Resize End,Width:" + rectangle.getWidth() + ",Height:" + rectangle.getHeight());
    }

    public static void main(String[] args) {
        Square square = new Square(10L);
        resize(square);
    }
}
