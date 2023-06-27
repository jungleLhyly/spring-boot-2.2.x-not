package com.jungle.spring.runlistener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author liyan at 2023/6/27
 */
public class TestSpringApplicationRunListener implements SpringApplicationRunListener {

	public TestSpringApplicationRunListener(final SpringApplication sa, final String[] args) {

	}

	/**
	 * Called immediately when the run method has first started. Can be used for very
	 * early initialization.
	 */
	public void starting() {
		System.out.println("starting");
	}

	/**
	 * Called once the environment has been prepared, but before the
	 * {@link ApplicationContext} has been created.
	 *
	 * @param environment the environment
	 */
	public void environmentPrepared(final ConfigurableEnvironment environment) {
		System.out.println("environmentPrepared");
	}

	/**
	 * Called once the {@link ApplicationContext} has been created and prepared, but
	 * before sources have been loaded.
	 *
	 * @param context the application context
	 */
	public void contextPrepared(final ConfigurableApplicationContext context) {
		System.out.println("contextPrepared");
	}

	/**
	 * Called once the application context has been loaded but before it has been
	 * refreshed.
	 *
	 * @param context the application context
	 */
	public void contextLoaded(final ConfigurableApplicationContext context) {
		System.out.println("contextLoaded");
	}

	/**
	 * The context has been refreshed and the application has started but
	 * {@link CommandLineRunner CommandLineRunners} and {@link ApplicationRunner
	 * ApplicationRunners} have not been called.
	 *
	 * @param context the application context.
	 * @since 2.0.0
	 */
	public void started(final ConfigurableApplicationContext context) {
		System.out.println("started");
	}

	/**
	 * Called immediately before the run method finishes, when the application context has
	 * been refreshed and all {@link CommandLineRunner CommandLineRunners} and
	 * {@link ApplicationRunner ApplicationRunners} have been called.
	 *
	 * @param context the application context.
	 * @since 2.0.0
	 */
	public void running(final ConfigurableApplicationContext context) {
		System.out.println("running");
	}

	/**
	 * Called when a failure occurs when running the application.
	 *
	 * @param context   the application context or {@code null} if a failure occurred before
	 *                  the context was created
	 * @param exception the failure
	 * @since 2.0.0
	 */
	public void failed(final ConfigurableApplicationContext context, final Throwable exception) {
		System.out.println("failed");
	}
}
