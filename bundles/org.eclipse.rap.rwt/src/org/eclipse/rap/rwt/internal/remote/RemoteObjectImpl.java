/*******************************************************************************
 * Copyright (c) 2012, 2013 EclipseSource and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    EclipseSource - initial API and implementation
 ******************************************************************************/
package org.eclipse.rap.rwt.internal.remote;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.rap.json.JsonObject;
import org.eclipse.rap.rwt.internal.protocol.JsonUtil;
import org.eclipse.rap.rwt.internal.protocol.ProtocolMessageWriter;
import org.eclipse.rap.rwt.internal.service.ContextProvider;
import org.eclipse.rap.rwt.internal.util.ParamCheck;
import org.eclipse.rap.rwt.remote.RemoteObject;
import org.eclipse.rap.rwt.remote.OperationHandler;


public class RemoteObjectImpl implements RemoteObject, Serializable {

  private final String id;
  private final List<RenderRunnable> renderQueue;
  private boolean created;
  private boolean destroyed;
  private OperationHandler handler;

  public RemoteObjectImpl( final String id, final String createType ) {
    this.id = id;
    destroyed = false;
    renderQueue = new ArrayList<RenderRunnable>();
    if( createType != null ) {
      renderQueue.add( new RenderRunnable() {
        public void render( ProtocolMessageWriter writer ) {
          writer.appendCreate( id, createType );
        }
      } );
    }
  }

  public String getId() {
    return id;
  }

  public void set( final String name, final int value ) {
    ParamCheck.notNullOrEmpty( name, "name" );
    checkState();
    renderQueue.add( new RenderRunnable() {
      public void render( ProtocolMessageWriter writer ) {
        writer.appendSet( id, name, value );
      }
    } );
  }

  public void set( final String name, final double value ) {
    ParamCheck.notNullOrEmpty( name, "name" );
    checkState();
    renderQueue.add( new RenderRunnable() {
      public void render( ProtocolMessageWriter writer ) {
        writer.appendSet( id, name, value );
      }
    } );
  }

  public void set( final String name, final boolean value ) {
    ParamCheck.notNullOrEmpty( name, "name" );
    checkState();
    renderQueue.add( new RenderRunnable() {
      public void render( ProtocolMessageWriter writer ) {
        writer.appendSet( id, name, value );
      }
    } );
  }

  public void set( final String name, final String value ) {
    ParamCheck.notNullOrEmpty( name, "name" );
    checkState();
    renderQueue.add( new RenderRunnable() {
      public void render( ProtocolMessageWriter writer ) {
        writer.appendSet( id, name, value );
      }
    } );
  }

  public void set( final String name, final Object value ) {
    ParamCheck.notNullOrEmpty( name, "name" );
    checkState();
    renderQueue.add( new RenderRunnable() {
      public void render( ProtocolMessageWriter writer ) {
        writer.appendSet( id, name, JsonUtil.createJsonValue( value ) );
      }
    } );
  }

  public void listen( final String eventType, final boolean listen ) {
    ParamCheck.notNullOrEmpty( eventType, "eventType" );
    checkState();
    renderQueue.add( new RenderRunnable() {
      public void render( ProtocolMessageWriter writer ) {
        writer.appendListen( id, eventType, listen );
      }
    } );
  }

  public void call( final String method, final Map<String, Object> parameters ) {
    ParamCheck.notNullOrEmpty( method, "method" );
    checkState();
    renderQueue.add( new RenderRunnable() {
      public void render( ProtocolMessageWriter writer ) {
        writer.appendCall( id, method, convertToJson( parameters ) );
      }
    } );
  }

  // TODO [rst] Temporary, removed when RemoteObject#call signature changed
  private JsonObject convertToJson( final Map<String, Object> properties ) {
    if( properties == null ) {
      return null;
    }
    JsonObject result = new JsonObject();
    for( Entry<String, Object> element : properties.entrySet() ) {
      result.add( element.getKey(), JsonUtil.createJsonValue( element.getValue() ) );
    }
    return result;
  }

  public void destroy() {
    checkState();
    renderQueue.add( new RenderRunnable() {
      public void render( ProtocolMessageWriter writer ) {
        writer.appendDestroy( id );
      }
    } );
    destroyed = true;
  }

  public boolean isDestroyed() {
    return destroyed;
  }

  public void setHandler( OperationHandler handler ) {
    this.handler = handler;
  }

  public OperationHandler getHandler() {
    return handler;
  }

  public void render( ProtocolMessageWriter writer ) {
    if( destroyed && !created ) {
      // skip rendering for objects that are disposed just after creation (see bug 395272)
    } else {
      for( RenderRunnable runnable : renderQueue ) {
        runnable.render( writer );
      }
      created = true;
    }
    renderQueue.clear();
  }

  void checkState() {
    // TODO [rst] Prevent calls with fake context as they break thread confinement
    if( !ContextProvider.hasContext() ) {
      throw new IllegalStateException( "Remote object called from wrong thread" );
    }
    if( destroyed ) {
      throw new IllegalStateException( "Remote object is destroyed" );
    }
  }

  public interface RenderRunnable {

    void render( ProtocolMessageWriter writer );

  }

}
