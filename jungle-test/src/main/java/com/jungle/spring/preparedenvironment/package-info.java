/**
 * @author liyan at 2023/6/27
 * springboot启动的第一件事就是准备环境变量
 * 所有的环境变量都会保存在{@link org.springframework.core.env.ConfigurableEnvironment}中
 * ===========
 * ConfigurableEnvironment中保存着两个信息
 * 1.环境变量  2.profiles运行环境
 * 环境变量是保存在 MutablePropertySources 中
 * MutablePropertySources 保存环境变量是保存在 List<PropertySource<?>> propertySourceList中
 * 不同的propertySource代表不同来源的环境变量
 * ===========
 * 启动时首先会将 systemPropery 和 systemEnvironment 两个propertySource加入进去
 * 如果是web环境，也会添加servletContextInitParams、servletConfigInitParams、jndiProperties 加入进去
 * 然后将 commandLine propertySource加入进去
 * 以上步骤完成以后，会发布 preparedEnvironmentEvent 事件
 * {@link org.springframework.boot.context.config.ConfigFileApplicationListener} 会监听这个事件
 * ConfigFileAl 在preparedEnvironmentEvent事件的回调处理中，会读取spring.properties里面配置的
 * {@link org.springframework.boot.env.EnvironmentPostProcessor} 实现类
 * 同时也会将自己加入到processor列表里面（ConfigFileAl也是EnvironmentPostProcessor的实现类）
 * 然后循环调用所有processor的postProcessEnvironment方法
 * 因为ConfigFileAl是列表的最后一个，所以会在最后一个执行
 * COnfigFileAl的postProcessEnvironment方法中，会读取spring.properties里面配置的
 * {@link org.springframework.boot.env.PropertySourceLoader} 实现类
 * 然后挨个调用loader的load方法，由loader来从各自关注的地方加载变量
 * 默认实现的loader有
 * {@link org.springframework.boot.env.PropertiesPropertySourceLoader}
 * 和
 * {@link org.springframework.boot.env.YamlPropertySourceLoader}
 * 这两个loader主要就是去找 application.properties 和 application.yml 读取配置的变量
 */
package com.jungle.spring.preparedenvironment;

/**
 * 配置中心的实现
 */
enum configCenterExam {
	/**
	 * 实现ApplicationListener子类，监听PreparedEnvironmentEvent事件，
	 * 在事件的回调函数中，去配置中心查询配置，并将配置加入到 environment 中
	 * 但是此时，application.properties/yml 文件还没有被读取，所以没有办法动态配置地址等信息
	 */
	实现ApplicationListener子类,

	/**
	 * 实现EnvironmentPostProcessor子类，ConfigFileAl收到preparedEnvironmentEvent事件的时候，
	 * 会挨个调用EnvironmentPostProcessor的postProcessEnvironment方法，
	 * 可以在这个回调方法中，去配置中心查询配置，并将配置加入到environment中
	 * 但是此时，application.properties/yml 文件还没有被读取，所以没有办法动态配置地址等信息
	 */
	实现EnvironmentPostProcessor子类,

	/**
	 * 实现PropertySourceLoader子类，ConfigFileAl会回调load方法，
	 * 在load方法中 ，去配置中心查询配置，并将配置作为load方法的返回值返回给ConfigFileAl，他会将返回的变量加入到environment中
	 * <p>
	 * 因为PropertiesPropertySourceLoader和YamlPropertySourceLoader会在最前边执行，
	 * 所以调用到的自定义的load方法时，application.properties/yml中配置的变量已经在environment中了，
	 * 此时就可以动态配置地址等信息，在load时从environment中获取
	 */
	实现PropertySourceLoader子类;
}