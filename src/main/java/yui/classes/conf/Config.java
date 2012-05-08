package yui.classes.conf;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
}
