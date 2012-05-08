package yui.classes.conf;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Map;


public class ConfigLoader {
  public static final Type TYPE_OF_MODULE_MAP = new TypeToken<Map<String, Module>>() {
  }.getType();
  
  private final Gson gson;

  public ConfigLoader() {
    gson = new GsonBuilder().registerTypeAdapter(ModuleType.class, new ModuleTypeAdapter()).create();
  }

  public Config getConfig(String version) {
    Config config = new Config(version);

    final String resourceName = "json_" + version + ".txt";
    InputStream inputStream = ConfigLoader.class.getResourceAsStream(resourceName);
    if (inputStream == null) {
      throw new IllegalArgumentException("unknown version " + version + ", " + resourceName + " does not exist.");
    }
    config.addModules(moduleMapFromReader(new InputStreamReader(inputStream)));

    return config;
  }

  public Map<String, Module> moduleMapFromReader(Reader reader) {
    return gson.fromJson(reader, TYPE_OF_MODULE_MAP);
  }
  
  public Map<String, Module> moduleMapFromJson(String json) {
    return gson.fromJson(json, TYPE_OF_MODULE_MAP);
  }

  protected Module moduleFromJson(String json) {
    return gson.fromJson(json, Module.class);
  }

  private class ModuleTypeAdapter implements JsonDeserializer<ModuleType>, JsonSerializer<ModuleType> {
    @Override
    public ModuleType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                           throws JsonParseException {
      return ModuleType.valueOf(json.getAsString());
    }

    @Override
    public JsonElement serialize(ModuleType src, Type typeOfSrc, JsonSerializationContext context) {
      return new JsonPrimitive(src.name());
    }
  }

}
