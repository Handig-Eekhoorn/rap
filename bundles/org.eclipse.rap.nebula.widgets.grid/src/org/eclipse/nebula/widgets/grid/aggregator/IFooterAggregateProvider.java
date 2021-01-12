package org.eclipse.nebula.widgets.grid.aggregator;

import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

/**
 * This interface is used to have aggregates of all data shown
 * displayed in the footer's text.
 *
 * The Grid will perform calls on this aggregate as follows:
 *  1.) {@link #clear()}
 *  2.) for each row, {@link #update(Object)} with this column's value as parameter
 *  3.) {@link #getResult()} to set the final footer text
 *
 * @author Hannes Erven
 */
public interface IFooterAggregateProvider {

	/**
	 * Aggregate the data.
	 * @return .
	 */
	String getResult();

	/**
	 * Reset internal state.
	 */
	void clear();

	/**
	 * Add one more value. Null may or may not be supported.
	 * @param val
	 */
	void update(Object val);

	/**
	 * Special font to use.
	 * @return .
	 */
	Font getFont();

	/**
	 * Image to show
	 * @return .
	 */
	Image getImage();

//	/**
//	 * A completely custom renderer
//	 * @return .
//	 */
//	GridFooterRenderer getFooterRenderer();
}
