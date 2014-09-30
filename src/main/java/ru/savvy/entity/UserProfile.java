package ru.savvy.entity;

import org.eclipse.persistence.annotations.Customizer;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.history.HistoryPolicy;
import org.eclipse.persistence.sessions.factories.DescriptorCustomizer;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author sasa <a href="mailto:sasa7812@gmail.com">Alexander Nikitin</a>
 */
@Entity
@Customizer(EclipseLinkHistoryCustomizer.class)
public class UserProfile extends AbstractAuditableEntity {

    @Column(name = "description")
    private String description;

    // ---------------------------------------------//

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

