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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "READOUT_DATE")
    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate readoutDate;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE }, fetch=FetchType.EAGER)
    @JoinTable(name = "METERS_DATA_RECORDS", joinColumns = @JoinColumn(name = "METERS_DATA_ID"), inverseJoinColumns = @JoinColumn(name = "METER_RECORD_ID"))
    private Set<MeterRecord> records = new HashSet<>();

    public LocalDate getReadoutDate() {
        return readoutDate;
    }

    public void setReadoutDate(LocalDate readoutDate) {
        this.readoutDate = readoutDate;
    }

    public Set<MeterRecord> getRecords() {
        return records;
    }

    public void setRecords(Set<MeterRecord> records) {
        this.records = records;
    }

    public void addRecord(MeterRecord record) {
        records.add(record);
    }
}
