/**
 * @author liyan at 2023/6/27
 * spring启动监听器，会在run方法中加载，
 * 然后在启动的每个关键节点调用 listener的各个方法
 * Spring自身有一个 {@link org.springframework.boot.context.event.EventPublishingRunListener}
 * 用来在各个启动节点进行事件发布
 * {@link org.springframework.context.ApplicationListener} 的实现类
 * 可以监听事件的发布以及接收回调
 */
package com.jungle.spring.runlistener;