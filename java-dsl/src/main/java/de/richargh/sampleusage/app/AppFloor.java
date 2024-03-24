package de.richargh.sampleusage.app;

import de.richargh.sampleusage.auth.AuthFloor;
import de.richargh.sampleusage.kernel.KernelFloor;

public interface AppFloor extends AuthFloor, KernelFloor {

    Books books();
}
