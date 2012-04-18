package yui.classes.conf;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;


public class ConfigLoader {
  private final Gson gson;

  public ConfigLoader() {
    gson = new GsonBuilder().registerTypeAdapter(ModuleType.class, new ModuleTypeAdapter()).create();
  }

  public Module fromJson(JsonObject moduleDefinition) {
    return null;
  }

  public Module fromJson(String json) {
    return gson.fromJson(json, Module.class);
  }

  private class ModuleTypeAdapter implements JsonDeserializer<ModuleType> {
    @Override
    public ModuleType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                           throws JsonParseException {
      ModuleType ret = ModuleType.valueOf(json.getAsString());
      return ret;
    }
  }
}
