/*******************************************************************************
 * Copyright (c) 2012 EclipseSource and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    EclipseSource - initial API and implementation
 ******************************************************************************/
package org.eclipse.rap.rwt.internal.client;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.rap.rwt.client.service.URLLauncher;
import org.eclipse.rap.rwt.internal.remote.RemoteObject;
import org.eclipse.rap.rwt.internal.remote.RemoteObjectFactory;


public class URLLauncherImpl implements URLLauncher {

  private static final String TYPE = "rwt.client.URLLauncher";
  private static final String OPEN_URL = "openURL";
  private RemoteObject remoteObject = RemoteObjectFactory.getInstance().createServiceObject( TYPE );

  public void openURL( String url ) {
    Map< String, Object > properties = new HashMap< String, Object >();
    properties.put( "url", url );
    remoteObject.call( OPEN_URL, properties );
  }

}