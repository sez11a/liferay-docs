package com.liferay.docs.headless.guestbook.client.function;

import javax.annotation.Generated;

/**
 * @author Rich Sezov
 * @generated
 */
@FunctionalInterface
@Generated("")
public interface UnsafeSupplier<T, E extends Throwable> {

	public T get() throws E;

}