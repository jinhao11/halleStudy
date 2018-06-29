package com.halle.java.base.proxy.jdk;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.*;

public class ProxyTest {
    public static void main(String[] args) {
        int count = 2147483647;
        while(true){
            count++;
            if(count++ == 0){
                break;
            }
        }
        XInterface xInterfaceImpl = (XInterface)Proxy.newProxyInstance(ProxyTest.class.getClassLoader(),new Class[]{XInterface.class},new MyInvocationHandler());
        xInterfaceImpl.doX(1);
        //xInterfaceImpl.hashCode();
        /*Method[] methods = xInterfaceImpl.getClass().getMethods();
        for (Method method : methods) {
            System.out.println(method.getName()+":  "+method.getDeclaringClass());
        }*/
    }

    static class MyInvocationHandler implements InvocationHandler{
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            try {
                if (Object.class.equals(method.getDeclaringClass())) {
                    return method.invoke(this, args);
                } else if (isDefaultMethod(method)) {
                    return invokeDefaultMethod(proxy, method, args);
                }
            } catch (Throwable t) {
                throw t;
            }

            return null;
        }
    }

    private static Object invokeDefaultMethod(Object proxy, Method method, Object[] args)
            throws Throwable {
        final Constructor<MethodHandles.Lookup> constructor = MethodHandles.Lookup.class
                .getDeclaredConstructor(Class.class, int.class);
        if (!constructor.isAccessible()) {
            constructor.setAccessible(true);
        }
        final Class<?> declaringClass = method.getDeclaringClass();
        return constructor
                .newInstance(declaringClass,
                MethodHandles.Lookup.PRIVATE | MethodHandles.Lookup.PROTECTED
                        | MethodHandles.Lookup.PACKAGE | MethodHandles.Lookup.PUBLIC)
                .unreflectSpecial(method, declaringClass).bindTo(proxy).invokeWithArguments(args);
}

    private static boolean isDefaultMethod(Method method) {
        return ((method.getModifiers()
                & (Modifier.ABSTRACT | Modifier.PUBLIC | Modifier.STATIC)) == Modifier.PUBLIC)
                && method.getDeclaringClass().isInterface();
    }
}
