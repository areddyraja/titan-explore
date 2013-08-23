/**
 * 
 */

package com.imaginea.titan.inf;

import com.imaginea.titan.exception.SystemException;
import com.imaginea.titan.execs.AggregateExecutor;
import com.imaginea.titan.execs.AverageExecutor;
import com.imaginea.titan.execs.BottomExecutor;
import com.imaginea.titan.execs.TopExecutor;

/**
 * @author mpujari
 *
 */
public enum Operation
{
    AGGREGATE("SUM", AggregateExecutor.class),
    AVERAGE("AVG", AverageExecutor.class),
    GREATER_THAN(">", null),
    LESS_THAN("<", null),
    EQUALS("==", null),
    GREATER_THAN_EQ_TO(">=", null),
    LESS_THAN_EQ_TO("<=", null), //
    TOP("TOP", TopExecutor.class),
    BOTTOM("BOTTOM", BottomExecutor.class);

    private final String symbol;
    private final Class<? extends OperationExec> executor;

    private Operation(final String symbol, final Class<? extends OperationExec> executor)
    {
        this.symbol = symbol;
        this.executor = executor;
    }

    /**
     * @param symbol
     * @return
     */
    public static Operation parse(final String symbol)
    {
        for (final Operation op : values()) {
            if (op.symbol.equalsIgnoreCase(symbol)) {
                return op;
            }
        }

        throw new SystemException("Undefined Operation");
    }

    /**
     * @return
     */
    public String getSymbol()
    {
        return symbol;
    }

    /**
     * @return
     */
    public Class<? extends OperationExec> getExecutor()
    {
        return executor;
    }

}
