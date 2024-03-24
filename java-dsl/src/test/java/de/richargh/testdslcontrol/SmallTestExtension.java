package de.richargh.testdslcontrol;

import de.richargh.sampleusage.app.AppFloor;
import de.richargh.sampleusage.app.SmallAppFloor;

public class SmallTestExtension extends BaseTestExtension {

    @Override
    protected AppFloor floor() {
        return new SmallAppFloor();
    }

}