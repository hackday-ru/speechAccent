package hackday.speechAccent.dto;

/**
 * Created by nicaraguanec on 16.04.2016.
 */
public class Rate {
    private String recordName;
    private int lastRate;
    private int averageRate;

    public Rate() {}

    public Rate(String recordName, int lastRate, int averageRate) {
        this.recordName = recordName;
        this.lastRate = lastRate;
        this.averageRate = averageRate;
    }

    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    public int getLastRate() {
        return lastRate;
    }

    public void setLastRate(int lastRate) {
        this.lastRate = lastRate;
    }

    public int getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(int averageRate) {
        this.averageRate = averageRate;
    }
}
