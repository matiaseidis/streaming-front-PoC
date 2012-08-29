package utils;

import java.lang.reflect.Type;

import play.data.binding.Global;
import play.data.binding.TypeBinder;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Global
public class JsonObjectBinder implements TypeBinder<JsonObject> {

	@Override
	public Object bind(String name,
			java.lang.annotation.Annotation[] annotations, String value,
			Class actualClass, Type genericType) throws Exception {
		return new JsonParser().parse(value);
	}

}
