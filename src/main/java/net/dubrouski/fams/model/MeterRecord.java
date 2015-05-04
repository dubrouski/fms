package net.dubrouski.fams.model;

import net.dubrouski.fams.model.enums.MeterType;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by tmarton on 4/12/15.
 */

@Entity
@Table(name = "METER_RECORD")
public class MeterRecord  implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "METER_TYPE")
    @Enumerated(EnumType.STRING)
    private MeterType meterType;

    @Column(name = "VALUE"
            , nullable = false
            , columnDefinition = " Decimal(10,2) default 0.0")
    private BigDecimal value = new BigDecimal(0);


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MeterType getMeterType() {
        return meterType;
    }

    public void setMeterType(MeterType meterType) {
        this.meterType = meterType;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
