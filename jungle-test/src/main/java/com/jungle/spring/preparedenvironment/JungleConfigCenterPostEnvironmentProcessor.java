package com.jungle.spring.preparedenvironment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author liyan at 2023/6/27
 */
public class JungleConfigCenterPostEnvironmentProcessor implements EnvironmentPostProcessor {
	/**
	 * Post-process the given {@code environment}.
	 *
	 * @param environment the environment to post-process
	 * @param application the application to which the environment belongs
	 */
	@Override
	public void postProcessEnvironment(final ConfigurableEnvironment environment, final SpringApplication application) {
		final MutablePropertySources propertySources = environment.getPropertySources();
		final Properties properties = new Properties();
		try {
			properties.load(this.getClass().getClassLoader().getResourceAsStream("jungle1.properties"));
		} catch (final IOException e) {
			e.printStackTrace();
		}

		final Map<String, String> map = new HashMap<>();

		for (final String stringPropertyName : properties.stringPropertyNames()) {
			map.put(stringPropertyName, properties.getProperty(stringPropertyName));
		}
		propertySources.addLast(new JunglePropertySource("jungleProperty1", map));
	}

	public static class JunglePropertySource extends PropertySource<Map<String, String>> {

		public JunglePropertySource(final String name, final Map<String, String> map) {
			super(name, map);
		}

		@Override
		@Nullable
		public Object getProperty(final String name) {
			return this.source.get(name);
		}
	}
}
