/*
 * Copyright (c) 2011-2013 GoPivotal, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package reactor.core.composable.spec;

import reactor.core.Environment;
import reactor.core.composable.Promise;
import reactor.core.spec.support.DispatcherComponentSpec;
import reactor.event.dispatch.Dispatcher;
import reactor.function.Supplier;
import reactor.util.Assert;

/**
 * @author Jon Brisbin
 * @author Stephane Maldini
 */
public final class PromiseSpec<T> extends DispatcherComponentSpec<PromiseSpec<T>, Promise<T>> {

	private T             value;
	private Supplier<T>   valueSupplier;
	private Throwable     error;

	public PromiseSpec<T> success(T value) {
		Assert.isNull(error, "Cannot set both a value and an error. Use one or the other.");
		Assert.isNull(valueSupplier, "Cannot set both a value and a Supplier. Use one or the other.");
		this.value = value;
		return this;
	}

	public PromiseSpec<T> success(Supplier<T> valueSupplier) {
		Assert.isNull(error, "Cannot set both an error and a Supplier. Use one or the other.");
		Assert.isNull(value, "Cannot set both a value and a Supplier. Use one or the other.");
		this.valueSupplier = valueSupplier;
		return this;
	}

	public PromiseSpec<T> error(Throwable error) {
		Assert.isNull(value, "Cannot set both a value and an error. Use one or the other.");
		Assert.isNull(valueSupplier, "Cannot set both an error and a Supplier. Use one or the other.");
		this.error = error;
		return this;
	}

	@Override
	protected Promise<T> configure(Dispatcher dispatcher, Environment env) {
		if (value != null) {
			return new Promise<T>(value, dispatcher, env);
		} else if (valueSupplier != null) {
			return new Promise<T>(valueSupplier, dispatcher, env);
		} else if (error != null) {
			return new Promise<T>(error, dispatcher, env);
		} else {
			throw new IllegalStateException("A success value/supplier or error reason must be provided. Use " +
				DeferredPromiseSpec.class.getSimpleName() + " to create a deferred promise");
		}
	}
}
