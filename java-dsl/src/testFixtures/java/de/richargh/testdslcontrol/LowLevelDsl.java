package de.richargh.testdslcontrol;

import de.richargh.sampleusage.app.AppFloor;
import de.richargh.sampleusage.kernel.DataFabrications;

public record LowLevelDsl(AppTestState testState, AppFloor floor) {

    public static LowLevelDsl of(AppFloor floor) {
        return new LowLevelDsl(new AppTestState(floor.clock(), DataFabrications.create(), floor.ids()), floor);
    }
}
