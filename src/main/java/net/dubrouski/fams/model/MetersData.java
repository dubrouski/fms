package net.dubrouski.fams.model;

import net.dubrouski.fams.converter.LocalDatePersistenceConverter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by tmarton on 4/12/15.
 */
@Entity
@Table(name = "METER_DATA")
public class MetersData {

    @Id
    private long id;

    @Column(name = "READOUT_DATE")
    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate readoutDate;

    @OneToMany
    @JoinTable(name = "METERS_DATA_RECORDS", joinColumns = @JoinColumn(name = "METERS_DATA_ID"), inverseJoinColumns = @JoinColumn(name = "METER_RECORD_ID"))
    private Set<MeterRecord> records = new HashSet<>();

    public Set<MeterRecord> getRecords() {
        return records;
    }

    public void setRecords(Set<MeterRecord> records) {
        this.records = records;
    }
}
