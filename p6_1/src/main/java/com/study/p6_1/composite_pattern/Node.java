package com.study.p6_1.composite_pattern;

import java.util.ArrayList;
import java.util.List;

public class Node extends BaseNode{
    private int level;
    private List<BaseNode> childs = new ArrayList<>();
    public Node(String name, int level) {
        super(name);
        this.level = level;
    }

    @Override
    protected void show() {
        System.out.println(this.name);
        for (BaseNode root : this.childs) {
            for (int i = 0; i < this.level; i++) {
                System.out.print(" ");
            }
            for (int i = 0; i < this.level; i++) {
                if (i == 0) {
                    System.out.print(" + ");
                }
                System.out.print("-");
            }
            root.show();
        }
    }

    public void addChild(BaseNode node){
        childs.add(node);
    }

    public void removeChild(BaseNode root){
        childs.remove(root);
    }

}
