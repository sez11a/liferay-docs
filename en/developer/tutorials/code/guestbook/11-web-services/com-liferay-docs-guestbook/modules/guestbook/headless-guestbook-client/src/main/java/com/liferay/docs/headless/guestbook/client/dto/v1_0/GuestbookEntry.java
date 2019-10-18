package com.liferay.docs.headless.guestbook.client.dto.v1_0;

import com.liferay.docs.headless.guestbook.client.function.UnsafeSupplier;
import com.liferay.docs.headless.guestbook.client.serdes.v1_0.GuestbookEntrySerDes;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Rich Sezov
 * @generated
 */
@Generated("")
public class GuestbookEntry {

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEmail(
		UnsafeSupplier<String, Exception> emailUnsafeSupplier) {

		try {
			email = emailUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String email;

	public Guestbook getGuestbook() {
		return guestbook;
	}

	public void setGuestbook(Guestbook guestbook) {
		this.guestbook = guestbook;
	}

	public void setGuestbook(
		UnsafeSupplier<Guestbook, Exception> guestbookUnsafeSupplier) {

		try {
			guestbook = guestbookUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Guestbook guestbook;

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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setMessage(
		UnsafeSupplier<String, Exception> messageUnsafeSupplier) {

		try {
			message = messageUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String message;

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

		if (!(object instanceof GuestbookEntry)) {
			return false;
		}

		GuestbookEntry guestbookEntry = (GuestbookEntry)object;

		return Objects.equals(toString(), guestbookEntry.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return GuestbookEntrySerDes.toJSON(this);
	}

}