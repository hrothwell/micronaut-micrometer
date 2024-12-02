/*
 * Copyright 2017-2024 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.configuration.metrics.condition;

import io.micronaut.aop.MethodInvocationContext;
import io.micronaut.core.annotation.Introspected;

import java.util.function.Predicate;

@FunctionalInterface
@Introspected
public interface MetricCondition extends Predicate<MethodInvocationContext> {
    boolean matches(MethodInvocationContext context);

    default boolean test(MethodInvocationContext condition) {
        return this.matches(condition);
    }

    /**
     * TODO I am not sure how the {@link io.micronaut.context.condition.Condition} are instantiated
     * @param clazz
     * @return
     */
    static MetricCondition getInstance(Class<? extends MetricCondition> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            // TODO logger
            return null;
        }
    }
}
