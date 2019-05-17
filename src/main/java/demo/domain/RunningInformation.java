package demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Random;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Entity
@Table(name = "RUNNING_INFORMATION")

public class RunningInformation {

    public enum HealthWarningLevel {
        LOW, NORMAL, HIGH
    }

    @Id
    @GeneratedValue
    private Long id;

    // Embed UserInfo

    @Embedded
    private final UserInfo userInfo;

    private String runningId;
    private double longitude;
    private double latitude;
    private double runningDistance;
    private double totalRunningTime;

    private int heartRate;
    private HealthWarningLevel healthWarningLevel;

    private Date timestamp = new Date();

    public RunningInformation() {

        this.userInfo = null;
    }

    public RunningInformation(String username, String address) {
        this.userInfo = new UserInfo(username, address);
    }

    public RunningInformation(
            @JsonProperty("runningId")  String runningId,
            @JsonProperty("latitude"    )String latitude,
            @JsonProperty("longitude")  String longitude,
            @JsonProperty("runningDistance")    String runningDistance,
            @JsonProperty("totalRunningTime")   String totalRunningTime,
            @JsonProperty("heartRate")  String heartRate,
            @JsonProperty("timestamp")  String timestamp,
            @JsonProperty("userInfo")   UserInfo userInfo
            ) {
        this.runningId = runningId;
        this.latitude = Double.parseDouble(latitude);
        this.longitude = Double.parseDouble(longitude);
        this.runningDistance = Double.parseDouble(runningDistance);
        this.totalRunningTime = Double.parseDouble(totalRunningTime);
        this.timestamp = new Date();
        this.userInfo = userInfo;

        this.heartRate = _getRandomHeartRate(60, 200);

        if ( this.heartRate > 120 ) {
            this.healthWarningLevel = HealthWarningLevel.HIGH;
        } else if (this.heartRate > 75) {
            this.healthWarningLevel = HealthWarningLevel.NORMAL;
        } else if (this.heartRate >= 60) {
            this.healthWarningLevel = HealthWarningLevel.LOW;
        } else {
            // Intentionally left blank
        }
        System.out.println(this.heartRate);
    }

    public RunningInformation(final UserInfo userIndo) {
        this.userInfo = userIndo;
    }

    public String getUsername() {
        return this.userInfo == null ? null : this.userInfo.getUsername();
    }

    public String getAddress() {
        return this.userInfo == null ? null : this.userInfo.getAddress();
    }
    private int _getRandomHeartRate(int min, int max) {
        Random rn = new Random();
        return min + rn.nextInt(max - min + 1);
    }
}
