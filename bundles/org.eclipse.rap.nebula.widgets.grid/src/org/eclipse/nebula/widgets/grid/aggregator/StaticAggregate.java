package org.eclipse.nebula.widgets.grid.aggregator;

/**
 * Static aggregate. always returns the static result.
 * @author h.erven
 *
 */
public class StaticAggregate extends AbstractFooterAggregateProvider {

	private String val;

	/**
	 *
	 * @param val
	 */
	public StaticAggregate(final String val) {
		super();
		this.val = val;
	}

	/**
	 *
	 */
	@Override
  public void clear() {
		// null
	}

	/**
	 * @return .
	 */
	@Override
  public String getResult() {
		return this.val;
	}

	/**
	 * @param val
	 */
	@Override
  public void update(Object val) {
		// null
	}

}
