/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.usergrid.persistence.entities;

import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.usergrid.persistence.TypedEntity;
import org.apache.usergrid.persistence.annotations.EntityProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@XmlRootElement
public class EnterpriseID extends TypedEntity {
	public static final String ENTITY_TYPE = "idm";
	
	public static final String PROPERTY_UUID = "uuid";

	@EntityProperty(indexed = true, fulltextIndexed = false, required = false, aliasProperty = true, unique = true, basic = true)
	private String name;
	@EntityProperty
	private Boolean enableLDAP;
	@EntityProperty
	private Boolean createUserNotExist;
	@EntityProperty
	private String endpointURL;
	@EntityProperty
	private String userSearchBase;
	@EntityProperty
	private String userIdAttribute;
	
	public EnterpriseID() {
		// TODO Auto-generated constructor stub
	}	
	
	public EnterpriseID(UUID id) {
		uuid =id;	
	}	

	@Override
	@JsonSerialize(include = Inclusion.NON_NULL)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name = "enableLDAP")
	public Boolean getEnableLDAP() {
		return enableLDAP;
	}

	public void setEnableLDAP(Boolean enableLDAP) {
		this.enableLDAP = enableLDAP;
	}

	@XmlElement(name = "createUserNotExist")
	public Boolean getCreateUserNotExist() {
		return createUserNotExist;
	}

	public void setCreateUserNotExist(Boolean createUserNotExist) {
		this.createUserNotExist = createUserNotExist;
	}

	@XmlElement(name = "endpointURL")
	public String getEndpointURL() {
		return endpointURL;
	}

	public void setEndpointURL(String endpointURL) {
		this.endpointURL = endpointURL;
	}

	@XmlElement(name = "userSearchBase")
	public String getUserSearchBase() {
		return userSearchBase;
	}

	public void setUserSearchBase(String userSearchBase) {
		this.userSearchBase = userSearchBase;
	}

	@XmlElement(name = "userIdAttribute")
	public String getUserIdAttribute() {
		return userIdAttribute;
	}

	public void setUserIdAttribute(String userIdAttribute) {
		this.userIdAttribute = userIdAttribute;
	}	
	
	
}
