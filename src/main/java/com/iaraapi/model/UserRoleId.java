package com.iaraapi.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Objects;
import java.util.UUID;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleId {
    public Long roleId;
    public UUID userId;
    public Long factoryId;

    @Override
    public boolean equals(Object o) {
        if (isAObject(o)) return true;
        if (isUserRoleId(o)) return false;
        UserRoleId that = (UserRoleId) o;
        return Objects.equals(roleId, that.roleId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(factoryId, that.factoryId);
    }

    private boolean isUserRoleId(Object o) {
        return !(o instanceof UserRoleId);
    }

    private boolean isAObject(Object o) {
        return this == o;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, userId, factoryId);
    }
}
