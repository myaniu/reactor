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

package reactor.core.dynamic.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import reactor.event.selector.ObjectSelector;
import reactor.event.selector.Selector;

/**
 * Annotation to denote that a method should proxy a call to
 * {@link reactor.core.Reactor#on(Selector, reactor.function.Consumer)}.
 *
 * @author Jon Brisbin
 * @author Andy Wilkinson
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface On {

	/**
	 * The string to use to create the {@link Selector}.
	 *
	 * @return the selector string
	 *
	 * @see ObjectSelector
	 */
	String value() default "";

}
