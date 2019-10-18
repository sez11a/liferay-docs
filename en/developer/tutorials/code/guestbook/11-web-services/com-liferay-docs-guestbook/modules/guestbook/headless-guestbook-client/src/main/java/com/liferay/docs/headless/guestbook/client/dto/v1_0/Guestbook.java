package com.liferay.docs.headless.guestbook.client.dto.v1_0;

import com.liferay.docs.headless.guestbook.client.function.UnsafeSupplier;
import com.liferay.docs.headless.guestbook.client.serdes.v1_0.GuestbookSerDes;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Rich Sezov
 * @generated
 */
@Generated("")
public class Guestbook {

	public Creator getCreator() {
		return creator;
	}

	public void setCreator(Creator creator) {
		this.creator = creator;
	}

	public void setCreator(
		UnsafeSupplier<Creator, Exception> creatorUnsafeSupplier) {

		try {
			creator = creatorUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Creator creator;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setId(UnsafeSupplier<String, Exception> idUnsafeSupplier) {
		try {
			id = idUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setName(UnsafeSupplier<String, Exception> nameUnsafeSupplier) {
		try {
			name = nameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String name;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Guestbook)) {
			return false;
		}

		Guestbook guestbook = (Guestbook)object;

		return Objects.equals(toString(), guestbook.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return GuestbookSerDes.toJSON(this);
	}

}