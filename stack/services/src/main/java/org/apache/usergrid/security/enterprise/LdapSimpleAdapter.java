package org.apache.usergrid.security.enterprise;

import java.util.Hashtable;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import org.apache.usergrid.persistence.entities.EnterpriseID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LdapSimpleAdapter implements AuthAdapter {

	private static final Logger logger = LoggerFactory
			.getLogger(LdapSimpleAdapter.class);

	private EnterpriseID enterpriseID;

	@Override
	public boolean authenticate(final String username, final String password) {
		final String dn = "uid=" + username + "," + enterpriseID.getUserSearchBase();
		final String ldapURL = enterpriseID.getEndpointURL();

		Hashtable<String, String> environment = new Hashtable<String, String>();
		environment.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		environment.put(Context.PROVIDER_URL, ldapURL);
		environment.put(Context.SECURITY_AUTHENTICATION, "simple");
		environment.put(Context.SECURITY_PRINCIPAL, dn);
		environment.put(Context.SECURITY_CREDENTIALS, password);

		try {
			DirContext authContext = new InitialDirContext(environment);
			logger.debug("User " + username
					+ " authenticated successfully with LDAP");
			authContext.close();
			return true;
		} catch (AuthenticationException ex) {
			logger.debug("User " + username
					+ " did NOT authenticate successfully with LDAP");
		} catch (NamingException ex) {
			logger.info("Encountered unexpected error: " + ex.getMessage());
		}

		return false;
	}

	public boolean enabled() {
		return enterpriseID.getEnableLDAP();
	}

	public boolean createUserIfNotExist() {
		return enterpriseID.getCreateUserNotExist();
	}	

	@Override
	public void setEnterpriseID(EnterpriseID enterpriseID) {
		this.enterpriseID = enterpriseID;
	}

}
