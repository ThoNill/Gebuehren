package configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.commons.configuration.AbstractConfiguration;
import org.apache.commons.configuration.CombinedConfiguration;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalINIConfiguration;
import org.apache.commons.configuration.Lock;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;
import org.apache.commons.configuration.tree.NodeCombiner;
import org.apache.commons.configuration.tree.UnionCombiner;

public class Config extends CombinedConfiguration {
	public static final Config CONFIG = new Config();

	private Config() {
		super(new UnionCombiner());

		try {
			addConfiguration(new SystemConfiguration());
			loadConfigFile("config/win.properties", null);
			loadIniFile("DBConnection.ini", "db");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Configuration getDbConfiguration(String name) throws FileNotFoundException,
			ConfigurationException {
		Configuration c = getConfiguration(name);
		if (c == null) {
			InputStream in = getStream("db/" + name + ".properties");
			if (in != null) {
				PropertiesConfiguration p = new PropertiesConfiguration();
				p.load(in);

				CompositeConfiguration dbConfig = new CompositeConfiguration();
				dbConfig.addConfiguration(p);

				String section = p.getString("section");

				HierarchicalINIConfiguration db = (HierarchicalINIConfiguration) getConfiguration("db");
				Configuration s = db.getSection(section);
				addDbConfig(dbConfig, s);

				addConfiguration(dbConfig, name);
			}
		}
		return c;
	}

	private void addDbConfig(CompositeConfiguration dbConfig, Configuration s) {
		dbConfig.addConfiguration(s);

	}


	protected void loadConfigFile(String name, String section)
			throws FileNotFoundException, ConfigurationException {
		InputStream in = getStream(name);
		if (in != null) {
			PropertiesConfiguration p = new PropertiesConfiguration();
			p.load(in);
			if (section != null) {
				addConfiguration(p, section);
			} else {
				addConfiguration(p);
			}
		}
	}

	private InputStream getStream(String name) throws FileNotFoundException {
		File file = new File(name);
		if (file.exists()) {
			return new FileInputStream(file);
		}
		return Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(name);
	}

	protected void loadIniFile(String name, String section)
			throws FileNotFoundException, ConfigurationException {
		InputStream in = getStream(name);
		if (in != null) {
			HierarchicalINIConfiguration p = new HierarchicalINIConfiguration();
			p.load(in);
			if (section != null) {
				addConfiguration(p, section);
			} else {
				addConfiguration(p);
			}
		}
	}
	
}
