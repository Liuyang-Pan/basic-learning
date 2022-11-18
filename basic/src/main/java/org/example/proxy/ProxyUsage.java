package org.example.proxy;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * purpose:代理用法
 *
 * @author Pan Liuyang
 * 2022/11/16 15:31
 */
public class ProxyUsage {
    @Test
    public void test() {
        ProxyInterface proxy = ExecutiveAgent.getProxy(new ProxyInterfaceImpl());
        proxy.executive();
    }
}

interface ProxyInterface {
    void executive();
}

class ProxyInterfaceImpl implements ProxyInterface {

    @Override
    public void executive() {
        System.out.println("executive...");
    }
}

class ExecutiveAgent {
    public static ProxyInterface getProxy(ProxyInterfaceImpl agent) {
        Object o = Proxy.newProxyInstance(agent.getClass().getClassLoader(), agent.getClass().getInterfaces(), (proxy, method, args) -> {
            System.out.println("代理执行方法前执行");
            Object invoke = method.invoke(agent, args);
            System.out.println("代理执行方法后执行");
            return invoke;
        });
        return ((ProxyInterface) o);
    }
}