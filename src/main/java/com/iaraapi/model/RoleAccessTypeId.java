package com.iaraapi.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RoleAccessTypeId implements Serializable {
    private Long roleId;
    private Long accessTypeId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleAccessTypeId)) return false;
        RoleAccessTypeId that = (RoleAccessTypeId) o;
        return Objects.equals(roleId, that.roleId) &&
                Objects.equals(accessTypeId, that.accessTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, accessTypeId);
    }
}
