package yui.classes.conf;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Combo {
  
  private final List<Module> scripts = new ArrayList<Module>();
  private final List<Module> styles  = new ArrayList<Module>();
  private boolean loadOptional = false;

  public void addModule(Module module) {
    if (module.getType() == ModuleType.js) {
      scripts.add(module);
    }
    else if (module.getType() == ModuleType.css) {
      styles.add(module);
    }
  }
  
  public boolean hasModule(Module module) {
    return scripts.contains(module) || styles.contains(module);
  }

  public List<Module> getScripts() {
    return scripts;
  }

  public List<Module> getStyles() {
    return styles;
  }

  public void setLoadOptional(boolean loadOptional) {
    this.loadOptional = loadOptional;
  }

  public boolean isLoadOptional() {
    return loadOptional;
  }
}
