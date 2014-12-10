package org.apache.usergrid.security.enterprise;

import org.apache.usergrid.persistence.entities.EnterpriseID;

public interface AuthAdapter {

  public boolean authenticate(final String username, final String password);

  public boolean enabled();

  public boolean createUserIfNotExist();
  
  public void setEnterpriseID(EnterpriseID enterpriseID);

}
