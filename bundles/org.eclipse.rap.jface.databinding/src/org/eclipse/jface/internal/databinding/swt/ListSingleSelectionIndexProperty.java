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

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.List;

/**
 * @since 3.3
 * 
 */
public class ListSingleSelectionIndexProperty extends
		SingleSelectionIndexProperty<List> {
	/**
	 * 
	 */
	public ListSingleSelectionIndexProperty() {
		super(new int[] { SWT.Selection, SWT.DefaultSelection });
	}

	@Override
	protected int doGetIntValue(List source) {
		return source.getSelectionIndex();
	}

	@Override
	protected void doSetIntValue(List source, int value) {
		if (value == -1)
			source.deselectAll();
		else
			source.setSelection(value);
	}

	@Override
	public String toString() {
		return "List.selectionIndex <int>"; //$NON-NLS-1$
	}
}
