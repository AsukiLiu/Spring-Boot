package org.asuki.springboot.component;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Profile;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

public interface NotificationSender {

    interface Profiles {
        String MAIL = "mail";
        String TELEPHONE = "tel";
        String DEVELOPMENT = "dev";
    }

    @Component
    @Profile(value = Profiles.MAIL)
    class EmailNotificationSender implements NotificationSender {
    }

    @Component
    @Profile(value = Profiles.TELEPHONE)
    class TelephoneNotificationSender implements NotificationSender {
    }

    @Component
    @Conditional(value = NoneNotificationSender.ProfileCondition.class)
    class NoneNotificationSender implements NotificationSender {

        static class ProfileCondition implements Condition {
            @Override
            public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
                return accepts(context, Profiles.DEVELOPMENT)
                        && !accepts(context, Profiles.MAIL)
                        && !accepts(context, Profiles.TELEPHONE);
            }

            private boolean accepts(ConditionContext context, String profile) {
                return context.getEnvironment().acceptsProfiles(profile);
            }
        }
    }
}
