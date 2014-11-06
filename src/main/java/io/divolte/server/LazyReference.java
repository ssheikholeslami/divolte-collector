/*
 * Copyright 2014 GoDataDriven B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.divolte.server;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
final class LazyReference<T> {
    @Nonnull
    private final Supplier<T> generator;

    @Nonnull
    private Optional<T> reference = Optional.empty();

    public LazyReference(@Nonnull final Supplier<T> generator) {
        this.generator = Objects.requireNonNull(generator);
    }

    public T get() {
        T result;
        if (reference.isPresent()) {
            result = reference.get();
        } else {
            result = generator.get();
            reference = Optional.of(result);
        }
        return result;
    }
}
