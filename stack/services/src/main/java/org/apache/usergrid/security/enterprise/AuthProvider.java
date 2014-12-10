package org.apache.usergrid.security.enterprise;

import java.util.UUID;

public class AuthProvider {

  private static AuthAdapter ldapSimpleAdapter = new LdapSimpleAdapter();
  
  public static AuthAdapter getAuthAdapterForApplication(UUID applicationId) {   
    AuthType type = AuthType.LDAP_SIMPLE;
    
    switch (type) {
      case LDAP_SIMPLE:
        return ldapSimpleAdapter;
    }
    
    return null;
  }

}
