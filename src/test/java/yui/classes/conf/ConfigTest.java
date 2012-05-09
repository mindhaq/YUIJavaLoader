package yui.classes.conf;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ConfigTest {
  
  private final Config config = new ConfigLoader().getConfig("3.4.1");
  
  @Test
  public void should_load_combo_with_no_dependency() {
    Combo combo = config.comboFor(true, "yui");
    
    assertThat(combo, is(not(nullValue())));
    assertScripts(combo, "yui");
    assertThat(combo.getStyles(), hasSize(0));
  }

  @Test
  public void should_load_combo_with_simple_dependency() {
    Combo combo = config.comboFor(true, "yui-log");  // requires yui-base

    assertThat(combo, is(not(nullValue())));
    assertScripts(combo, "yui-base", "yui-log");
    assertThat(combo.getStyles(), hasSize(0));
  }

  private void assertScripts(Combo combo, String... expectedModules) {
    assertThat(combo.getScripts(), contains(modulesFor(expectedModules)));
  }

  private void assertStyles(Combo combo, String... expectedModules) {
    assertThat(combo.getStyles(), contains(modulesFor(expectedModules)));
  }

  private Module[] modulesFor(String... moduleNames) {
    Module[] modules = new Module[moduleNames.length];
    for (int i = 0; i < moduleNames.length; i++) {
      modules[i] = config.getModule(moduleNames[i]);
    }
    return modules;
  }
}
