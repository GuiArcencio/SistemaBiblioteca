package app.Exception;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import com.google.common.primitives.Primitives;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class AnnotatedDeserializer<T> implements JsonDeserializer<T> {

private final Gson gson = new Gson();

public T deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {

    T target = gson.fromJson(je, type);
    checkRequired(target);
    return target;
}

private List<Field> findMissingFields(Object target, List<Field> invalidFields) {

    for (Field field : target.getClass().getDeclaredFields()) {
        if (field.getAnnotation(JsonRequired.class) != null) {

            Object fieldValue = ReflectionUtil.getFieldValue(target, field);

            if (fieldValue == null) {
                invalidFields.add(field);
                continue;
            }

            if (!isPrimitive(fieldValue)) {
                findMissingFields(fieldValue, invalidFields);
            }
        }
    }
    return invalidFields;
}

private void checkRequired(Object target) {

    List<Field> invalidFields = Lists.newArrayList();
    findMissingFields(target, invalidFields);

    if (!invalidFields.isEmpty()) {
        throw new JsonParseException("Missing JSON required fields: {"
                + FluentIterable.from(invalidFields).transform(toMessage).join(Joiner.on(", ")) + "}");
    }
}

static Function<Field, String> toMessage = new Function<Field, String>() {
    @Override
    public String apply(Field field) {
        return field.getDeclaringClass().getName() + "/" + field.getName();
    }
};

private boolean isPrimitive(Object target) {

    for (Class<?> primitiveClass : Primitives.allPrimitiveTypes()) {
        if (primitiveClass.equals(target.getClass())) {
            return true;
        }
    }
    return false;
}

public static class RequiredFieldAwareGsonBuilder {

    private GsonBuilder gsonBuilder;

    private RequiredFieldAwareGsonBuilder(GsonBuilder gsonBuilder) {
        this.gsonBuilder = gsonBuilder;
    }

    public static RequiredFieldAwareGsonBuilder builder() {
        return new RequiredFieldAwareGsonBuilder(new GsonBuilder());
    }

    public <T> RequiredFieldAwareGsonBuilder withRequiredFieldAwareType(Class<T> classOfT) {
        gsonBuilder.registerTypeAdapter(classOfT, new AnnotatedDeserializer<T>());
        return this;
    }

    public Gson build() {
        return gsonBuilder.create();
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public static @interface JsonRequired {
}
}