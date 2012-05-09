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

  public Combo comboFor(boolean loadOptional, String... modules) {
    Combo combo = new Combo();
    combo.setLoadOptional(loadOptional);

    addModules(combo, modules);

    return combo;
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
      
      combo.addModule(module);
    }
  }

  private void addModules(Combo combo, Set<String> requires) {
    addModules(combo, requires.toArray(new String[requires.size()]));
  }
}
