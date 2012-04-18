package yui.classes.conf;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Module {
  private final String name;

  private final ModuleType type;

  private final String path;

  private boolean external = false;

  private boolean skinnable = false;

  private boolean langPack = false;

  private Condition condition;

  private final Set<String> requires = new HashSet<String>();

  private final Set<String> supersedes = new HashSet<String>();

  private final Set<String> lang = new HashSet<String>();

  private final Set<String> use = new HashSet<String>();

  private final Set<String> after = new HashSet<String>();

  private final Map<String, Boolean> afterMap = new HashMap<String, Boolean>();

  public Module(String name, ModuleType type, String path) {
    this.name = name;
    this.type = type;
    this.path = path;
  }

  public String getName() {
    return name;
  }

  public ModuleType getType() {
    return type;
  }

  public String getPath() {
    return path;
  }

  public Set<String> getRequires() {
    return requires;
  }

  public Set<String> getSupersedes() {
    return supersedes;
  }

  public Set<String> getLang() {
    return lang;
  }

  public Set<String> getUse() {
    return use;
  }

  public Set<String> getAfter() {
    return after;
  }

  public Map<String, Boolean> getAfterMap() {
    return afterMap;
  }

  public boolean isExternal() {
    return external;
  }

  public void setExternal(boolean external) {
    this.external = external;
  }

  public boolean isSkinnable() {
    return skinnable;
  }

  public void setSkinnable(boolean skinnable) {
    this.skinnable = skinnable;
  }

  public boolean isLangPack() {
    return langPack;
  }

  public void setLangPack(boolean langPack) {
    this.langPack = langPack;
  }

  public Condition getCondition() {
    return condition;
  }

  public void setCondition(Condition condition) {
    this.condition = condition;
  }
}
