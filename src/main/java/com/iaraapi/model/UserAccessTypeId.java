package com.iaraapi.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccessTypeId implements Serializable {
    private UUID userId;
    private Integer accessTypeId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccessTypeId)) return false;
        UserAccessTypeId that = (UserAccessTypeId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(accessTypeId, that.accessTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, accessTypeId);
    }
}
