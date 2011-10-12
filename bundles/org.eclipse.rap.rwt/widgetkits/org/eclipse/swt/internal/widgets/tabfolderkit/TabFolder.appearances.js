/*******************************************************************************
 * Copyright (c) 2007, 2011 Innoopract Informationssysteme GmbH and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Innoopract Informationssysteme GmbH - initial API and implementation
 *    EclipseSource - ongoing development
 ******************************************************************************/
appearances = {
// BEGIN TEMPLATE //

  "tab-view" : {
    style : function( states ) {
      var tv = new org.eclipse.swt.theme.ThemeValues( states );
      var result = {};
      result.textColor = tv.getCssColor( "*", "color" );
      result.font = tv.getCssFont( "TabFolder", "font" );
      result.spacing = -1;
      result.border = tv.getCssBorder( "TabFolder", "border" );
      return result;
    }
  },

  "tab-view-bar" : {
    style : function( states ) {
      return {
        height : "auto"
      };
    }
  },

  "tab-view-pane" : {
    style : function( states ) {
      var tv = new org.eclipse.swt.theme.ThemeValues( states );
      var result = {};
      result.overflow = "hidden";
      result.backgroundColor = tv.getCssColor( "*", "background-color" );
      result.border = tv.getCssBorder( "TabFolder-ContentContainer", "border" );
      return result;
    }
  },

  "tab-view-page" : {
//		      style : function( states ) {
//		        return {
// TODO [rst] disappeared in qx 0.7
//		          top : 0,
//		          right : 0,
//		          bottom : 0,
//		          left : 0
//		        };
//		      }
  },
  
  "tab-view-button" : {
    include : "atom",

    style : function( states ) {
      var result = {};
      var tv = new org.eclipse.swt.theme.ThemeValues( states );
      var borderColor = tv.getCssNamedColor( "thinborder" );
      var top_color = tv.getCssColor( "TabItem", "border-top-color" );
      var bottom_color = tv.getCssColor( "TabItem", "border-bottom-color" );
      var checkedColorTop = [ top_color, borderColor, borderColor, borderColor ];
      var checkedColorBottom = [ borderColor, borderColor, bottom_color, borderColor ];
      if( states.checked ) {
        result.zIndex = 1; // TODO [rst] Doesn't this interfere with our z-order?
        if( states.barTop ) {
          result.border = new org.eclipse.rwt.Border( [ 3, 1, 0, 1 ], "solid", checkedColorTop );
        } else {
          result.border = new org.eclipse.rwt.Border( [ 0, 1, 3, 1 ], "solid", checkedColorBottom );
        }
        result.margin = [ 0, -1, 0, -2 ];
        if( states.firstChild ) {
          result.marginLeft = 0;
        }
      } else {
        result.zIndex = 0; // TODO [rst] Doesn't this interfere with our z-order?
        result.marginRight = 1;
        result.marginLeft = 0;
        if( states.barTop ) {
          result.border = new org.eclipse.rwt.Border( [ 1, 1, 0, 1 ], "solid", borderColor );
          result.marginTop = 3;
          result.marginBottom = 1;
        } else {
          result.border = new org.eclipse.rwt.Border( [ 0, 1, 1, 1 ], "solid", borderColor );
          result.marginTop = 1;
          result.marginBottom = 3;
        }
      }
      result.padding = tv.getCssBoxDimensions( "TabItem", "padding" );
      result.backgroundColor = tv.getCssColor( "TabItem", "background-color" );
      result.backgroundImage = tv.getCssImage( "TabItem", "background-image" );
      result.backgroundGradient = tv.getCssGradient( "TabItem", "background-image" );
      result.textShadow = tv.getCssShadow( "TabItem", "text-shadow" );
      return result;
    }
  }

// END TEMPLATE //
};