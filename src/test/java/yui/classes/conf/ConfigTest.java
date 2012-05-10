package yui.classes.conf;

import org.apache.commons.lang3.StringUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class ConfigTest {
  private final Config config = new ConfigLoader().getConfig("3.5.0");

  private static final Logger LOG = LoggerFactory.getLogger(ConfigTest.class);

  @Test
  public void should_load_combo_with_no_dependency() {
    Combo combo = config.comboForWithOptionals("yui");

    assertThat(combo, is(not(nullValue())));
    assertScripts(combo, "yui");
    assertThat(combo.getStyles(), hasSize(0));
  }

  @Test
  public void should_load_combo_with_simple_dependency() {
    Combo combo = config.comboForWithOptionals("yui-log"); // requires yui-base

    assertThat(combo, is(not(nullValue())));
    assertScripts(combo, "yui-base", "yui-log");
    assertThat(combo.getStyles(), hasSize(0));
  }

  @Test
  public void should_load_attribute_base_with_dependencies() {
    Combo combo = config.comboFor("attribute-base");

    assertThat(combo, is(not(nullValue())));
    assertScripts(combo, "yui-base", "attribute-core", "oop", "event-custom-base", "event-custom-complex",
      "attribute-events", "attribute-extras", "attribute-base");
    assertThat(combo.getStyles(), hasSize(0));
  }

  @Ignore
  @Test
  public void should_load_autocomplete_with_dependencies_and_styles() {
    Combo combo = config.comboFor("autocomplete");

    assertThat(combo, is(not(nullValue())));
    assertStyles(combo, "autocomplete-list", "widget-base");
    assertScripts(combo, "yui-base", "array-extras", "attribute-core", "base-core", "oop", "event-custom-base",
      "event-custom-complex", "attribute-events", "attribute-extras", "attribute-base", "attribute-complex",
      "base-base", "base-build", "escape", "features", "dom-core", "dom-base", "selector-native", "selector",
      "node-core", "node-base", "event-base", "event-synthetic", "event-focus", "event-valuechange",
      "autocomplete-base", "autocomplete-sources", "intl-base", "intl", "lang/autocomplete-list",
      "skin-sam-autocomplete-list", "event-resize", "dom-style", "dom-screen", "node-screen", "selector-css2",
      "selector-css3", "node-style", "pluginhost-base", "pluginhost-config", "node-pluginhost", "shim-plugin",
      "skin-sam-widget-base", "base-pluginhost", "classnamemanager", "widget-base", "widget-htmlparser", "widget-skin",
      "event-delegate", "node-event-delegate", "widget-uievents", "widget-position", "widget-position-align",
      "autocomplete-list", "autocomplete-list-keys", "autocomplete-plugin");
  }

  @Test
  public void should_process_after() {
    Combo combo = new Combo();
  }

  private void assertScripts(Combo combo, String... expectedModules) {
    LOG.info("expect: [" + StringUtils.join(expectedModules, ", ") + "]");
    LOG.info("actual: " + StringUtils.join(combo.getScripts()));
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
