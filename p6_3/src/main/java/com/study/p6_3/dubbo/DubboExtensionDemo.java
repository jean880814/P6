package com.study.p6_3.dubbo;

import org.apache.dubbo.common.compiler.Compiler;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.rpc.Protocol;

public class DubboExtensionDemo {
    public static void main(String[] args) {
        Protocol p = (Protocol) ExtensionLoader.getExtensionLoader(Protocol.class).getExtension("dubbo");
        Compiler e = (Compiler) ExtensionLoader.getExtensionLoader(Compiler.class).getAdaptiveExtension();
        System.out.println(e);
    }
}
