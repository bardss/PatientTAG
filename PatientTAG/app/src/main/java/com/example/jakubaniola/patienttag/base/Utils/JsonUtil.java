package com.example.jakubaniola.patienttag.base.Utils;

import android.util.Log;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

public class JsonUtil {

//    public static PatientTO jsonToPatient(JSONObject jsonResponse) throws JSONException {
//        Integer id =f jsonResponse.getInt("id");
//        String name = jsonResponse.getString("name");
//        String surname = jsonResponse.getString("surname");
//        String dateOfBirth = jsonResponse.getString("dateOfBirth");
//        Sex sex = jsonResponse.get;
//        return new PatientTO(id, name, surname);
//    }
    private static JsonUtil instance;
    private GsonBuilder builder;

    public static JsonUtil getInstance() {
        if(null == instance) {
            instance = new JsonUtil();
        }
        return instance;
    }

    private JsonUtil() {
        initialize();
    }

    private void initialize() {
        builder = new GsonBuilder();
        builder.setDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz").create();
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        });
    }

    public <T> T fromJson(String value, Type type) {
        T result = null;
        Log.i(JsonUtil.class.getName(), value);
        Log.i(JsonUtil.class.getName(), type.toString());
        try {
            if(List.class.isAssignableFrom(getClass(type))) {
                JSONArray json = new JSONArray(value);
                result = builder.create().fromJson(json.toString(), type);
            } else {
                JSONObject json = new JSONObject(value);
                result = builder.create().fromJson(json.toString(), type);
            }
        } catch (Exception e) {
            Log.e("JSONException", "Problem with parsing JSON" + "Type: " +  type.toString() + " Value: " + value, e);
        }
        return result;
    }

    public <T> String toJson(T value, Type type) {
        String json = builder.create().toJson(value, type);
        Log.i(JsonUtil.class.getName(), json);
        return json;
    }

    public <T> String toJson(T value, Class<T> clazz) {
        return toJson(value, (Type) clazz);
    }

    public static Class<?> getClass(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        } else if (type instanceof ParameterizedType) {
            return getClass(((ParameterizedType) type).getRawType());
        } else if (type instanceof GenericArrayType) {
            Type componentType = ((GenericArrayType) type).getGenericComponentType();
            Class<?> componentClass = getClass(componentType);
            if (componentClass != null ) {
                return Array.newInstance(componentClass, 0).getClass();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}

