package jx3api.api.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Objects;

public class OnEnableJX3ApiWSCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, @SuppressWarnings("NullableProblems") AnnotatedTypeMetadata metadata) {
        return !Objects.requireNonNull(context.getBeanFactory()).getBeansWithAnnotation(EnableJX3ApiWS.class).isEmpty();
    }
}
