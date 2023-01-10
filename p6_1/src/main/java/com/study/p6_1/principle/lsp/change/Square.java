package com.study.p6_1.principle.lsp.change;

/**
 * @program: P6
 * @author: Jean
 * @create: 2023-01-09 16:00
 */
public class Square implements QuadRangle {

    private long length;
    @Override
    public long getWidth() {
        return length;
    }

    @Override
    public long getHeight() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }
}
