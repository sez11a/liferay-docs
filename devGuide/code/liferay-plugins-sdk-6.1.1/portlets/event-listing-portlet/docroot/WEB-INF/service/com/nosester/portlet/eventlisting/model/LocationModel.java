/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.nosester.portlet.eventlisting.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the Location service. Represents a row in the &quot;Event_Location&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.nosester.portlet.eventlisting.model.impl.LocationModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.nosester.portlet.eventlisting.model.impl.LocationImpl}.
 * </p>
 *
 * @author jbloggs
 * @see Location
 * @see com.nosester.portlet.eventlisting.model.impl.LocationImpl
 * @see com.nosester.portlet.eventlisting.model.impl.LocationModelImpl
 * @generated
 */
public interface LocationModel extends BaseModel<Location> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a location model instance should use the {@link Location} interface instead.
	 */

	/**
	 * Returns the primary key of this location.
	 *
	 * @return the primary key of this location
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this location.
	 *
	 * @param primaryKey the primary key of this location
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the location ID of this location.
	 *
	 * @return the location ID of this location
	 */
	public long getLocationId();

	/**
	 * Sets the location ID of this location.
	 *
	 * @param locationId the location ID of this location
	 */
	public void setLocationId(long locationId);

	/**
	 * Returns the company ID of this location.
	 *
	 * @return the company ID of this location
	 */
	public long getCompanyId();

	/**
	 * Sets the company ID of this location.
	 *
	 * @param companyId the company ID of this location
	 */
	public void setCompanyId(long companyId);

	/**
	 * Returns the group ID of this location.
	 *
	 * @return the group ID of this location
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this location.
	 *
	 * @param groupId the group ID of this location
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the user ID of this location.
	 *
	 * @return the user ID of this location
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this location.
	 *
	 * @param userId the user ID of this location
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this location.
	 *
	 * @return the user uuid of this location
	 * @throws SystemException if a system exception occurred
	 */
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this location.
	 *
	 * @param userUuid the user uuid of this location
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the create date of this location.
	 *
	 * @return the create date of this location
	 */
	public long getCreateDate();

	/**
	 * Sets the create date of this location.
	 *
	 * @param createDate the create date of this location
	 */
	public void setCreateDate(long createDate);

	/**
	 * Returns the modified date of this location.
	 *
	 * @return the modified date of this location
	 */
	public long getModifiedDate();

	/**
	 * Sets the modified date of this location.
	 *
	 * @param modifiedDate the modified date of this location
	 */
	public void setModifiedDate(long modifiedDate);

	/**
	 * Returns the name of this location.
	 *
	 * @return the name of this location
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this location.
	 *
	 * @param name the name of this location
	 */
	public void setName(String name);

	/**
	 * Returns the description of this location.
	 *
	 * @return the description of this location
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this location.
	 *
	 * @param description the description of this location
	 */
	public void setDescription(String description);

	/**
	 * Returns the street address of this location.
	 *
	 * @return the street address of this location
	 */
	@AutoEscape
	public String getStreetAddress();

	/**
	 * Sets the street address of this location.
	 *
	 * @param streetAddress the street address of this location
	 */
	public void setStreetAddress(String streetAddress);

	/**
	 * Returns the city of this location.
	 *
	 * @return the city of this location
	 */
	@AutoEscape
	public String getCity();

	/**
	 * Sets the city of this location.
	 *
	 * @param city the city of this location
	 */
	public void setCity(String city);

	/**
	 * Returns the state or province of this location.
	 *
	 * @return the state or province of this location
	 */
	@AutoEscape
	public String getStateOrProvince();

	/**
	 * Sets the state or province of this location.
	 *
	 * @param stateOrProvince the state or province of this location
	 */
	public void setStateOrProvince(String stateOrProvince);

	/**
	 * Returns the country of this location.
	 *
	 * @return the country of this location
	 */
	@AutoEscape
	public String getCountry();

	/**
	 * Sets the country of this location.
	 *
	 * @param country the country of this location
	 */
	public void setCountry(String country);

	public boolean isNew();

	public void setNew(boolean n);

	public boolean isCachedModel();

	public void setCachedModel(boolean cachedModel);

	public boolean isEscapedModel();

	public Serializable getPrimaryKeyObj();

	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	public ExpandoBridge getExpandoBridge();

	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	public Object clone();

	public int compareTo(Location location);

	public int hashCode();

	public CacheModel<Location> toCacheModel();

	public Location toEscapedModel();

	public String toString();

	public String toXmlString();
}