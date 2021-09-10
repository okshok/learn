package ok.stock.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class DailyChart {
    private LocalDate date;
    private LocalTime minute;
    private String label;
    private long high;
    private long low;
    private long average;
    private long volume;
    private long notional;
    private long numberOfTrades;
    private long marketHigh;
    private long marketLow;
    private long marketAverage;
    private long marketVolume;
    private long marketNotional;
    private long marketNumberOfTrades;
    private long open;
    private long close;
    private long marketOpen;
    private long marketClose;
    private long changeOverTime;
    private long marketChangeOverTime;
}
