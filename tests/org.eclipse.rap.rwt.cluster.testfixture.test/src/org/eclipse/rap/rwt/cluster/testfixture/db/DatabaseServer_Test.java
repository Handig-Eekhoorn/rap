/*******************************************************************************
 * Copyright (c) 2011 EclipseSource and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    EclipseSource - initial API and implementation
 ******************************************************************************/
package org.eclipse.rap.rwt.cluster.testfixture.db;

import java.sql.*;

import junit.framework.TestCase;


public class DatabaseServer_Test extends TestCase {

  private DatabaseServer databaseServer;

  protected void setUp() throws Exception {
    databaseServer = new DatabaseServer();
    Class.forName( databaseServer.getDriverClassName() );
  }
  
  public void testGetConnectionUrl() {
    String connectionUrl = databaseServer.getConnectionUrl();
    
    assertEquals( "jdbc:h2:tcp://localhost:-1/mem:sessions;DB_CLOSE_DELAY=-1", connectionUrl );
  }
  
  public void testStart() throws Exception {
    databaseServer.start();
    String connectionUrl = databaseServer.getConnectionUrl();
    
    Connection connection = DriverManager.getConnection( connectionUrl );
    
    assertNotNull( connection );
  }
  
  public void testStop() throws Exception {
    databaseServer.start();
    String connectionUrl = databaseServer.getConnectionUrl();
    Connection connection = DriverManager.getConnection( connectionUrl );
    connection.close();
    
    databaseServer.stop();
    
    try {
      DriverManager.getConnection( connectionUrl );
      fail();
    } catch( SQLException expected ) {
    }
  }
}