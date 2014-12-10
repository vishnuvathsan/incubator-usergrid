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
package org.apache.usergrid.rest.management.users.organizations.config;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.apache.usergrid.management.OrganizationInfo;
import org.apache.usergrid.persistence.entities.EnterpriseID;
import org.apache.usergrid.rest.AbstractContextResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.apache.usergrid.utils.ConversionUtils.string;


@Component("org.apache.usergrid.rest.management.users.organizations.config.EnterpriseIDConfiguration")
@Scope("prototype")
@Produces({ MediaType.APPLICATION_JSON, "application/javascript",
		"application/x-javascript", "text/ecmascript",
		"application/ecmascript", "text/jscript" })
public class EnterpriseIDConfiguration extends AbstractContextResource {
	private static final Logger logger = LoggerFactory.getLogger(EnterpriseIDConfiguration.class);
	
	OrganizationInfo organization;

	public EnterpriseIDConfiguration init(OrganizationInfo organization) {
		this.organization = organization;
		return this;
	}

	@POST
	@Path("idm")
	@Consumes(MediaType.APPLICATION_JSON)
	public EnterpriseID saveEnterpriseIDConfiguration(EnterpriseID erpPluginData)throws Exception {		
		logger.info("create EnterpriseResourceData initialized");		
		EnterpriseID enterpriseID = management.saveEnterpriseIDConfiguration(organization,organization.getName(),erpPluginData.getEnableLDAP(),erpPluginData.getCreateUserNotExist(),erpPluginData.getEndpointURL(),erpPluginData.getUserSearchBase(),erpPluginData.getUserIdAttribute());		
		
		return enterpriseID;
	}

	@GET
	@Path("idm")
	public EnterpriseID getEnterpriseIDConfiguration()throws Exception {		
		logger.info("getEnterpriseResourceData initialized");		
		
        return management.getEnterpriseIDConfigurationByOrg(organization);		
	}
	
	@POST
	@Path("test")
	@Consumes(MediaType.APPLICATION_JSON)
	public Boolean testEnterpriseIDConfiguration(@Context UriInfo ui, Map<String, Object> json,
            @QueryParam("callback") @DefaultValue("callback") String callback)throws Exception{
		String username = string(json.get("username"));
		String password = string(json.get("password"));
		
		return management.testEnterpriseIDConfiguration(organization,username,password);
	}

}
