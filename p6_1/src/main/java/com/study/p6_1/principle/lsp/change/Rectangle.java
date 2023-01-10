package com.study.p6_1.principle.lsp.change;

/**
 * @program: P6
 * @author: Jean
 * @create: 2023-01-09 15:58
 */
public class Rectangle implements QuadRangle {

    private long height;
    private long width;
    @Override
    public long getWidth() {
        return width;
    }

    public void setWidth(long width) {
        this.width = width;
    }

    @Override
    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }
}
