package org.eclipse.nebula.widgets.grid.aggregator;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 *
 * @author h.erven
 *
 */
public class NumberSumAggregate extends AbstractFooterAggregateProvider {

	private BigDecimal state=BigDecimal.ZERO;
	private final NumberFormat format;

	/**
	 *
	 * @param format
	 */
	public NumberSumAggregate(final NumberFormat format) {
		this.format = format;
	}

	/**
	 */
	@Override
  public void clear() {
		this.state=BigDecimal.ZERO;
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
		if (val==null) {
      return;
    }

		if (!(val instanceof Number)){
			throw new IllegalArgumentException("Cannot accept '"+val.getClass().getCanonicalName()+"', only Number is allowed!");
		}
		final Number n = (Number) val;
		if (n instanceof BigDecimal){
			this.state = this.state.add((BigDecimal) n);
		}else{
			this.state = this.state.add(BigDecimal.valueOf(n.doubleValue()));
		}
	}

}
