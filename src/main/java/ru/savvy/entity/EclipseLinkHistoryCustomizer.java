package ru.savvy.entity;

import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.history.HistoryPolicy;
import org.eclipse.persistence.sessions.factories.DescriptorCustomizer;

/**
 * @author sasa <a href="mailto:sasa7812@gmail.com">Alexander Nikitin</a>
 */
public class EclipseLinkHistoryCustomizer implements DescriptorCustomizer {
    public void customize(ClassDescriptor descriptor) {
        HistoryPolicy policy = new HistoryPolicy();
        policy.addHistoryTableName("userprofile_hist");
        policy.addStartFieldName("hist_start_date");
        policy.addEndFieldName("hist_end_date");
        descriptor.setHistoryPolicy(policy);
    }
}
