package yui.classes.conf;

import java.util.*;


public class Config {
  public final String version;

  private final Map<String, Module> moduleMap = new HashMap<String, Module>();

  public Config(String version) {
    this.version = version;
  }

  public Module getModule(String name) {
    if (!moduleMap.containsKey(name)) {
      throw new IllegalArgumentException("module " + name + " not configured.");
    }

    return moduleMap.get(name);
  }

  public void addModule(Module module) {
    moduleMap.put(module.getName(), module);
  }

  public void addModules(Map<String, Module> moduleMap) {
    this.moduleMap.putAll(moduleMap);
  }

  public int getModuleCount() {
    return moduleMap.size();
  }

  /**
   * Create combo for selected modules, without optionals.
   * @param modules
   * @return
   */
  public Combo comboFor(String... modules) {
    return comboFor(false, modules);
  }

  /**
   * Create combo for selected modules
   * @param modules
   * @return
   */
  public Combo comboForWithOptionals(String... modules) {
    return comboFor(true, modules);
  }

  private Combo comboFor(boolean loadOptional, String... modules) {
    Combo combo = new Combo();
    combo.setLoadOptional(loadOptional);

    addModules(combo, modules);
    sortModules(combo);

    return combo;
  }

  private void sortModules(Combo combo) {
    List<Module> scripts = combo.getScripts();
    boolean unsorted = true;
    int i = 0;
sort:
    while ((i < 200) && unsorted) {
      unsorted = false; // assume it stays that way
      i++;

      for (Module module : scripts) {
        if (module.getAfter().isEmpty()) {
          continue;
        }

        for (String afterModuleName : module.getAfter()) {
          Module afterModule = getModule(afterModuleName);
          int afterPos = scripts.indexOf(afterModule);
          int modulePos = scripts.indexOf(module);

          if (modulePos < afterPos) {
            scripts.remove(afterModule);
            scripts.add(modulePos, afterModule);
            unsorted = true;
            continue sort;
          }
        }
      }
    }

    if (i >= 200) {
      throw new RuntimeException(
        "didn't finish sorting after 200 iterations; there seems to be a circular dependency.");
    }
  }

  private void addModules(Combo combo, String[] modules) {
    for (String moduleName : modules) {
      Module module = getModule(moduleName);
      if (combo.hasModule(module)) {
        continue;
      }

      addModules(combo, module.getRequires());
      if (combo.isLoadOptional()) {
        addModules(combo, module.getOptional());
      }

      // add rollup dependencies
      if (!module.getSupersedes().isEmpty()) {
        addModules(combo, module.getSupersedes());
      } else {
        combo.addModule(module);
      }
    }
  }

  private void addModules(Combo combo, Set<String> requires) {
    addModules(combo, requires.toArray(new String[requires.size()]));
  }
}
