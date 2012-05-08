package yui.classes.conf;

import org.hamcrest.Matchers;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


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
    assertThat(module.getPath(), is(equalTo("yui-base/yui-base-min.js")));
    assertThat(module.isExternal(), is(equalTo(false)));
    assertThat(module.getRequires(), is(Matchers.<String>empty()));
  }
  
  @Test
  public void should_load_js_moudle_with_requirements_and_optionals() throws ParseException {
    String json = "{\n" +
        "  \"optional\":[\"autocomplete-sources\"],\n" +
        "  \"requires\":[\"array-extras\", \"base-build\", \"escape\", \"event-valuechange\", \"node-base\"],\n" +
        "  \"name\":\"autocomplete-base\",\n" +
        "  \"type\":\"js\",\n" +
        "  \"path\":\"autocomplete-base/autocomplete-base-min.js\",\n" +
        "  \"ext\":false\n" +
        "}";
    Module module = configLoader.fromJson(json);

    assertThat(module, is(notNullValue()));
    assertThat(module.getName(), is(equalTo("autocomplete-base")));
    assertThat(module.getType(), is(equalTo(ModuleType.js)));
    assertThat(module.getPath(), is(equalTo("autocomplete-base/autocomplete-base-min.js")));
    assertThat(module.isExternal(), is(equalTo(false)));
    assertThat(module.getRequires(), containsInAnyOrder("array-extras", "base-build", "escape", "event-valuechange", "node-base"));
    assertThat(module.getOptional(), contains("autocomplete-sources"));
  }
  
  @Test
  public void should_load_module_with_use_lang_and_supersede() throws ParseException {
    String json = "{\n" +
        "  \"use\":[\"autocomplete-base\", \"autocomplete-sources\", \"autocomplete-list\", \"autocomplete-plugin\"],\n" +
        "  \"lang\":[\"en\", \"es\"],\n" +
        "  \"name\":\"autocomplete\",\n" +
        "  \"type\":\"js\",\n" +
        "  \"path\":\"autocomplete/autocomplete-min.js\",\n" +
        "  \"supersedes\":[\"autocomplete-base\", \"autocomplete-sources\", \"autocomplete-list\", \"autocomplete-plugin\"],\n" +
        "  \"ext\":false,\n" +
        "  \"requires\":[]\n" +
        "}";
    Module module = configLoader.fromJson(json);

    assertThat(module, is(notNullValue()));
    assertThat(module.getName(), is(equalTo("autocomplete")));
    assertThat(module.getType(), is(equalTo(ModuleType.js)));
    assertThat(module.getPath(), is(equalTo("autocomplete/autocomplete-min.js")));
    assertThat(module.isExternal(), is(equalTo(false)));
    assertThat(module.getRequires(), is(Matchers.<String>empty()));
    assertThat(module.getLang(), containsInAnyOrder("en", "es"));
    assertThat(module.isLangPack(), is(equalTo(false)));
    assertThat(module.getUse(), containsInAnyOrder("autocomplete-base", "autocomplete-sources", "autocomplete-list", "autocomplete-plugin"));
    assertThat(module.getSupersedes(), containsInAnyOrder("autocomplete-base", "autocomplete-sources", "autocomplete-list", "autocomplete-plugin"));
  }
  
  @Test
  public void should_load_language_pack() throws ParseException {
    String json = "{\n" +
        "  \"path\":\"dial/lang/dial_en.js\",\n" +
        "  \"intl\":true,\n" +
        "  \"langPack\":true,\n" +
        "  \"ext\":false,\n" +
        "  \"supersedes\":[],\n" +
        "  \"name\":\"lang/dial_en\",\n" +
        "  \"type\":\"js\",\n" +
        "  \"requires\":[]\n" +
        "}";
    Module module = configLoader.fromJson(json);

    assertThat(module, is(notNullValue()));
    assertThat(module.getName(), is(equalTo("lang/dial_en")));
    assertThat(module.getType(), is(equalTo(ModuleType.js)));
    assertThat(module.getPath(), is(equalTo("dial/lang/dial_en.js")));
    assertThat(module.isExternal(), is(equalTo(false)));
    assertThat(module.getRequires(), is(Matchers.<String>empty()));
    assertThat(module.getLang(),  is(Matchers.<String>empty()));
    assertThat(module.isLangPack(), is(equalTo(true)));
    assertThat(module.isIntl(), is(equalTo(true)));
  }

  @Test
  public void should_load_module_with_after_and_condition() throws ParseException {
    String json = "{\n" +
        "  \"condition\":{\n" +
        "    \"name\":\"history-hash-ie\",\n" +
        "    \"trigger\":\"history-hash\"\n" +
        "  },\n" +
        "  \"requires\":[\"history-hash\", \"node-base\"],\n" +
        "  \"name\":\"history-hash-ie\",\n" +
        "  \"type\":\"js\",\n" +
        "  \"path\":\"history-hash-ie/history-hash-ie-min.js\",\n" +
        "  \"ext\":false,\n" +
        "  \"after\":[\"history-hash\"],\n" +
        "  \"after_map\":{\n" +
        "    \"history-hash\":true\n" +
        "  }\n" +
        "}";
    Module module = configLoader.fromJson(json);

    assertThat(module, is(notNullValue()));
    assertThat(module.getName(), is(equalTo("history-hash-ie")));
    assertThat(module.getType(), is(equalTo(ModuleType.js)));
    assertThat(module.getPath(), is(equalTo("history-hash-ie/history-hash-ie-min.js")));
    assertThat(module.isExternal(), is(equalTo(false)));
    assertThat(module.getRequires(), containsInAnyOrder("history-hash", "node-base"));
    assertThat(module.getAfter(), containsInAnyOrder("history-hash"));
    assertThat(module.getLang(), is(Matchers.<String>empty()));
    
    Condition condition = module.getCondition();
    assertThat(condition, is(notNullValue()));
    assertThat(condition.getName(), is(equalTo("history-hash-ie")));
    assertThat(condition.getTrigger(), is(equalTo("history-hash")));
  }
  
  @Test
  public void should_load_css_module() throws ParseException {
    String json = "{\n" +
        "  \"type\":\"css\",\n" +
        "  \"name\":\"cssfonts\",\n" +
        "  \"path\":\"cssfonts/cssfonts-min.css\",\n" +
        "  \"ext\":false,\n" +
        "  \"requires\":[]\n" +
        "}";
    Module module = configLoader.fromJson(json);

    assertThat(module, is(notNullValue()));
    assertThat(module.getName(), is(equalTo("cssfonts")));
    assertThat(module.getType(), is(equalTo(ModuleType.css)));
    assertThat(module.getPath(), is(equalTo("cssfonts/cssfonts-min.css")));
  }
}
