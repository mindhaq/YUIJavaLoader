package yui.classes.conf;

import org.json.simple.parser.ParseException;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;


public class ConfigLoaderTest {
  private ConfigLoader configLoader = new ConfigLoader();

  @Test
  public void should_load_basic_js_module() throws ParseException {
    String basicJsDefinition = "{\n" +
      "  \"name\":\"yui-base\",\n" +
      "  \"type\":\"js\",\n" +
      "  \"path\":\"yui-base/yui-base-min.js\",\n" +
      "  \"ext\":false,\n" +
      "  \"requires\":[],\n" +
      "  \"_inspected\":true\n" +
      "}";

    Module module = configLoader.fromJson(basicJsDefinition);

    assertThat(module, is(notNullValue()));
    assertThat(module.getName(), is(equalTo("yui-base")));
    assertThat(module.getType(), is(equalTo(ModuleType.js)));
  }

}
