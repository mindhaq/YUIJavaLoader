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
import java.lang.reflect.Type;
import java.util.Collection;


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
