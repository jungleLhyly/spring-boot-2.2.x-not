package com.jungle.spring.preparedenvironment;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.core.env.PropertySource;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author liyan at 2023/6/27
 */
public class JungleConfigCenterApplicationListener implements SmartApplicationListener {
	/**
	 * Determine whether this listener actually supports the given event type.
	 *
	 * @param eventType the event type (never {@code null})
	 */
	@Override
	public boolean supportsEventType(final Class<? extends ApplicationEvent> eventType) {
		return eventType.isAssignableFrom(ApplicationEnvironmentPreparedEvent.class);
	}

	/**
	 * Handle an application event.
	 *
	 * @param event the event to respond to
	 */
	@Override
	public void onApplicationEvent(final ApplicationEvent event) {
		final ApplicationEnvironmentPreparedEvent preparedEvent = (ApplicationEnvironmentPreparedEvent)event;

		final Properties properties = new Properties();
		try {
			properties.load(this.getClass().getClassLoader().getResourceAsStream("jungle.properties"));
		} catch (final IOException e) {
			e.printStackTrace();
		}

		final Map<String, String> map = new HashMap<>();

		for (final String stringPropertyName : properties.stringPropertyNames()) {
			map.put(stringPropertyName, properties.getProperty(stringPropertyName));
		}

		final JunglePropertySource jungleProperty = new JunglePropertySource("jungleProperty", map);

		preparedEvent.getEnvironment().getPropertySources().addLast(jungleProperty);
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
