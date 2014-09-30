package ru.savvy.entity;

import org.joda.time.DateTime;
import org.springframework.data.domain.Auditable;
import ru.savvy.entity.converter.JodaDateTimeConverter;

import javax.persistence.*;
import javax.persistence.Convert;

/**
 * @author sasa <a href="mailto:sasa7812@gmail.com">Alexander Nikitin</a>
 */

@MappedSuperclass
@org.eclipse.persistence.annotations.Converter(name = "jodaDateTimeConverter", converterClass = JodaDateTimeConverter.class)
public abstract class AbstractAuditableEntity extends AbstractEntity implements Auditable<User,Long> {

    @ManyToOne
    @JoinColumn(name = "created_by_id", referencedColumnName = "id", nullable = false)
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "last_modified_by", referencedColumnName = "id")
    private User lastModifiedBy;

    @Column(name = "created_date", nullable = false)
    @Convert(converter = JodaDateTimeConverter.class)
    private DateTime createdDate;

    @Column(name = "last_modified_date")
    @Convert(converter = JodaDateTimeConverter.class)
    private DateTime lastModifiedDate;

    //---------------------------------------------//

    @Override
    public User getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public DateTime getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(DateTime creationDate) {
        this.createdDate = creationDate;
    }

    @Override
    public User getLastModifiedBy() {
        return lastModifiedBy;
    }

    @Override
    public void setLastModifiedBy(User lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Override
    public DateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    @Override
    public void setLastModifiedDate(DateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

}
