package org.eclipse.nebula.widgets.grid.aggregator;

import java.text.NumberFormat;
import java.util.function.Function;

/**
 * Simply counts any value supplied to {@link #update(Object)} (including null).
 * @author Hannes Erven
 */
public class CountAggregate extends AbstractFooterAggregateProvider {

	private long state=0l;
	private final NumberFormat format;
	private final Function<Object, Boolean> filterFunction;

	/**
	 *
	 * @param format
	 */
	public CountAggregate(final NumberFormat format) {
		this.format = format;
		this.filterFunction = null;
	}

	/**
	 *
	 * @param format
	 * @param filterFunction
	 */
	public CountAggregate(NumberFormat format, Function<Object, Boolean> filterFunction) {
		super();
		this.format = format;
		this.filterFunction = filterFunction;
	}

	/**
	 */
	@Override
  public void clear() {
		this.state=0l;
	}

	/**
	 * @return .
	 */
	@Override
  public String getResult() {
		return this.format.format(this.state);
	}

	/**
	 * @param val
	 */
	@Override
  public void update(Object val) {
		if(this.filterFunction == null || (this.filterFunction != null && this.filterFunction.apply(val).booleanValue())) {
			this.state++;
		}
	}

}
