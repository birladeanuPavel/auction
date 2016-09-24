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
                column = @Column(name = "WEIGHT_NAME")),
        @AttributeOverride(name = "symbol",
                column = @Column(name = "WEIGHT_SYMBOL"))
})
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
public class Weight extends Measurement {

    @NotNull
    @Column(name = "WEIGHT")
    protected BigDecimal value;

    protected Weight(){}

    public Weight(String name, String symbol, BigDecimal value) {
        super(name, symbol);
        this.value = value;
    }

}
