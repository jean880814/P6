package com.study.p6_1.principle.lsp;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @program: P6
 * @author: Jean
 * @create: 2022-12-21 15:37
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Square extends Rectangle{
    private long length;

    @Override
    public long getHeight() {
        return getLength();
    }

    @Override
    public void setHeight(long height) {
        setLength(height);
    }

    @Override
    public long getWidth() {
        return getLength();
    }

    @Override
    public void setWidth(long width) {
        setLength(width);
    }
}
