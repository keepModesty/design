package com.example.pattern.structural;

/**
 * @author ll
 * 这种模式涉及到一个单一的类，该类负责创建自己的对象，同时确保只有单个对象被创建。这个类提供了一种访问其唯一的对象的方式，可以直接访问，不需要实例化该类的对象。
 * 单例模式是一种创建型设计模式，它确保一个类只有一个实例，并提供了一个全局访问点来访问该实例。
 * <p>
 * 注意：
 * 1、单例类只能有一个实例。
 * 2、单例类必须自己创建自己的唯一实例。
 * 3、单例类必须给所有其他对象提供这一实例。
 * <p>
 * 意图：保证一个类仅有一个实例，并提供一个访问它的全局访问点。
 * 主要解决：一个全局使用的类频繁地创建与销毁。
 * 何时使用：当您想控制实例数目，节省系统资源的时候。
 * 如何解决：判断系统是否已经有这个单例，如果有则返回，如果没有则创建。
 * 关键代码：构造函数是私有的。
 */
public class Singleton {
    public static void main(String[] args) {
        //懒汉式
        SingletonLazy s1 = SingletonLazy.getInstance();
        SingletonLazySynchronized s2 = SingletonLazySynchronized.getInstance();
        //饿汉式
        SingletonStatic s3 = SingletonStatic.getInstance();
        //双检锁/双重校验锁
        SingletonDoubleCheckLock s4 = SingletonDoubleCheckLock.getInstance();
        //登记式
        SingletonHolder s5 = SingletonHolder.getInstance();
        //枚举
        SingletonEnum s6 = SingletonEnum.INSTANCE;
        System.out.println("1.懒汉式：" + s1 + "\n" +
                "2.懒汉式加锁：" + s2 + "\n" +
                "3.饿汉式：" + s3 + "\n" +
                "4.双检锁：" + s4 + "\n" +
                "5.登记式：" + s5 + "\n" +
                "6.枚举：" + s6 + "\n"
        );
    }
}

/**
 * 懒汉式，线程不安全
 */
class SingletonLazy {
    private static SingletonLazy instance;

    private SingletonLazy() {
        System.out.println("懒汉式 线程不安全 now created!");
    }

    public static SingletonLazy getInstance() {
        if (instance == null) {
            instance = new SingletonLazy();
        }
        return instance;
    }
}

/**
 * 懒汉式，线程安全
 */
class SingletonLazySynchronized {
    private static SingletonLazySynchronized instance;

    private SingletonLazySynchronized() {
        System.out.println("懒汉式，线程安全 now created!");
    }

    public static synchronized SingletonLazySynchronized getInstance() {
        if (instance == null) {
            instance = new SingletonLazySynchronized();
        }
        return instance;
    }
}

/**
 * 饿汉式，线程安全
 */
class SingletonStatic {
    private static SingletonStatic instance = new SingletonStatic();

    private SingletonStatic() {
        System.out.println("饿汉式，线程安全 now created!");
    }

    public static SingletonStatic getInstance() {
        return instance;
    }
}

/**
 * 双检锁，线程安全
 */
class SingletonDoubleCheckLock {
    private volatile static SingletonDoubleCheckLock instance;

    private SingletonDoubleCheckLock() {
        System.out.println("双检锁，线程安全 now created!");
    }

    public static SingletonDoubleCheckLock getInstance() {
        if (instance == null) {
            synchronized (SingletonDoubleCheckLock.class) {
                if (instance == null) {
                    instance = new SingletonDoubleCheckLock();
                }
            }
        }
        return instance;
    }
}

/**
 * 登记式/静态内部类
 */
class SingletonHolder {
    private static class Singleton {
        private static final SingletonHolder INSTANCE = new SingletonHolder();
    }

    private SingletonHolder() {
        System.out.println("登记式，线程安全 now created!");
    }

    public static final SingletonHolder getInstance() {
        return Singleton.INSTANCE;
    }
}

/**
 * 枚举
 */
enum SingletonEnum {
    INSTANCE;
    private SingletonEnum(){
        System.out.println("枚举，线程安全 now created!");
    }
}