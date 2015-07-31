package no.ica.fraf.modules;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import no.ica.fraf.gui.FrafMain;

import com.google.inject.Module;

public class ModuleFactory {
	public static List<Module> getModules() {
		List<Module> modules = new ArrayList<Module>();
		addFrafModule(modules);
		addFrafStandaloneModule(modules);
		return modules;
	}

	private static void addFrafStandaloneModule(final List<Module> modules) {
		try {
			if (FrafMain.isStandalone()) {
				Constructor<FrafStandaloneModule> constructor = FrafStandaloneModule.class
						.getDeclaredConstructor(null);
				constructor.setAccessible(true);

				modules.add(constructor.newInstance(null));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	private static void addFrafModule(final List<Module> modules) {
		try {
			Constructor<FrafModule> constructor = FrafModule.class
					.getDeclaredConstructor(null);
			constructor.setAccessible(true);

			modules.add(constructor.newInstance(null));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}
