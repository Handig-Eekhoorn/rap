package org.eclipse.nebula.widgets.grid.aggregator;

import java.text.NumberFormat;

/**
 * Simply counts any value supplied to {@link #update(Object)} (including null).
 * @author Hannes Erven
 */
public class CountAggregate extends AbstractFooterAggregateProvider {

	private long state=0l;
	private final NumberFormat format;

	/**
	 * 
	 * @param format
	 */
	public CountAggregate(final NumberFormat format) {
		this.format = format;
	}

	/**
	 */
	public void clear() {
		this.state=0l;
	}

	/**
	 * @return .
	 */
	public String getResult() {
		return this.format.format(this.state);
	}

	/**
	 * @param val  
	 */
	public void update(Object val) {
		this.state++;
	}

}
