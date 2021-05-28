package io.mvvm.bootstrap;

/**
 * @program: io-admin
 * @description: 启动器
 * 当你需要在服务器启动 {{@link #start(String...)}} 或者关闭 {{@link #shutdown()}} 的时候触发一些事件
 * 那么你就可以直接实现此接口
 * 我们会按照 {{@link #order()}} 方法提供的数值进行顺序调用
 * @author: Mr. Pan
 * @create: 2021-05-28 20:07
 **/
public interface IBootstrap {

    /**
     * 启动顺序,值越大，执行优先级越高
     *
     * @return int
     */
    int order();

    /**
     * 启动回调, 在系统启动时会调用此方法
     */
    void start(String... args);

    /**
     * 停止回调, 在系统停止的时候会调用此方法
     */
    void shutdown();

}
