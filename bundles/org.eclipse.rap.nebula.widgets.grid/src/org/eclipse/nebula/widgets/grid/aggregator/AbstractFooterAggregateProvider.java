package org.eclipse.nebula.widgets.grid.aggregator;

import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

/**
 * Base aggregate that returns null for all formatting options.
 * @author h.erven
 *
 */
public abstract class AbstractFooterAggregateProvider implements IFooterAggregateProvider {

	/**
	 * Default: null
	 * @return .
	 */
	@Override
    public Font getFont() {
		return null;
	}

//	/**
//	 * Default: null
//	 * @return .
//	 */
//	public GridFooterRenderer getFooterRenderer() {
//		return null;
//	}

	/**
	 * Default: null
	 * @return .
	 */
	@Override
	public Image getImage() {
		return null;
	}


}
