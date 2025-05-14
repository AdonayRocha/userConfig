package com.userConfig.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Inclui suporte para serialização/desserialização com Jackson.
 */
public enum RoleUser {
    ADMIN("Administrator"),
    USER("Regular User"),
    USER_GROUP("User Group"),
    ADMIN_GROUP("Administrator Group");

    // Campo imutável para o nome amigável do papel
    private final String displayName;

    RoleUser(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Retorna o nome amigável do papel para serialização JSON.
     */
    @JsonValue
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Converte um valor JSON em uma instância de RoleUser.
     * 
     * @param value O valor JSON a ser convertido.
     * @return A instância correspondente de RoleUser.
     * @throws IllegalArgumentException Se o valor não corresponder a nenhum papel.
     */
    @JsonCreator
    public static RoleUser fromDisplayName(String value) {
        for (RoleUser role : RoleUser.values()) {
            if (role.displayName.equalsIgnoreCase(value) || role.name().equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Função inválida: " + value);
    }
}
