package com.jungle.spring.preparedenvironment;

import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.util.*;

/**
 * @author liyan at 2023/6/27
 */
public class JungleConfigCenterPropertyLoader implements PropertySourceLoader {
	/**
	 * Returns the file extensions that the loader supports (excluding the '.').
	 *
	 * @return the file extensions
	 */
	@Override
	public String[] getFileExtensions() {
		return new String[] {"properties"};
	}

	/**
	 * Load the resource into one or more property sources. Implementations may either
	 * return a list containing a single source, or in the case of a multi-document format
	 * such as yaml a source for each document in the resource.
	 *
	 * @param name     the root name of the property source. If multiple documents are loaded
	 *                 an additional suffix should be added to the name for each source loaded.
	 * @param resource the resource to load
	 * @return a list property sources
	 * @throws IOException if the source cannot be loaded
	 */
	@Override
	public List<PropertySource<?>> load(final String name, final Resource resource) throws IOException {
		final Properties properties = new Properties();
		try {
			properties.load(resource.getInputStream());
		} catch (final IOException e) {
			e.printStackTrace();
		}

		final Map<String, String> map = new HashMap<>();

		for (final String stringPropertyName : properties.stringPropertyNames()) {
			map.put(stringPropertyName, properties.getProperty(stringPropertyName));
		}

		return Collections.singletonList(new JunglePropertySource("jungleProperty2", map));
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
