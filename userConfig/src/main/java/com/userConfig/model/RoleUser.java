package com.userConfig.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/** 
 *  Displayname for the friendly name of the role on the UI.
 *  The displayname is habilited used in the jSon
 *  The value name is habilited used in the jSon
 */

public enum RoleUser {
    ADMIN("Administrator"),
    USER("Regular User"),
    USER_GROUP("User Group"),
    ADMIN_GROUP("Administrator Group");

    // Immutable field
    private final String displayName;

    RoleUser(String displayName) {
        this.displayName = displayName;
    }

    @JsonValue
    public String getDisplayName() {
        return displayName;
    }

    @JsonCreator
    public static RoleUser fromDisplayName(String value) {
        for (RoleUser role : RoleUser.values()) {
            if (role.displayName.equalsIgnoreCase(value) || role.name().equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Função inválidas: " + value);
    }
}
