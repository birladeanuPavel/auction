package com.birladeanu.dal.model.embedabble;

import com.birladeanu.dal.model.parent.Measurement;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by pavel on 9/12/16.
 */
@Embeddable
@AttributeOverrides({
        @AttributeOverride(name = "name",
                column = @Column(name = "DIMENSION_NAME")),
        @AttributeOverride(name = "symbol",
                column = @Column(name = "DIMENSION_SYMBOL"))
})
@Getter
@EqualsAndHashCode
@ToString
public class Dimension extends Measurement {

    @NotNull
    protected BigDecimal depth;
    @NotNull
    protected BigDecimal height;
    @NotNull
    protected BigDecimal width;

    protected Dimension(){}

    public Dimension(String name, String symbol, BigDecimal depth, BigDecimal height, BigDecimal width) {
        super(name, symbol);
        this.depth = depth;
        this.height = height;
        this.width = width;
    }

}
