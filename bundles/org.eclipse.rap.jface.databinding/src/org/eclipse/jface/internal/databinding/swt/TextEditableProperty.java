/*******************************************************************************
 * Copyright (c) 2008, 2009 Matthew Hall and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Matthew Hall - initial API and implementation (bug 194734)
 ******************************************************************************/

package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.widgets.Text;

/**
 * @since 3.3
 * 
 */
public class TextEditableProperty extends WidgetBooleanValueProperty<Text> {
	@Override
	protected boolean doGetBooleanValue(Text source) {
		return source.getEditable();
	}

	@Override
	protected void doSetBooleanValue(Text source, boolean value) {
		source.setEditable(value);
	}

	@Override
	public String toString() {
		return "Text.editable <boolean>"; //$NON-NLS-1$
	}
}
