package site.toeicdoit.user.domain;

import lombok.AllArgsConstructor;

import java.util.stream.Stream;

@AllArgsConstructor
public enum RegistrationId {
    LOCAL("local"), GOOGLE("google");

    public final String name;

    public static RegistrationId findByName(String name) {
        return Stream.of(values()).filter(r -> r.name.equals(name)).findFirst().orElse(null);
    }
}
