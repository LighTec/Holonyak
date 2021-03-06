/*
 * This program was designed for a arduino Uno, or any similar device that can communicate over 
 * serial USB.
 * ---------------------------------------------------------
 * This program is designed to have the arduino itself run custom code, so do not expect
 * that you can run this on any random arduino. The code required to get the arduino to work
 * can be found as a .ino file recognizable by the arduino IDE, within the zip package of this
 * program.
 */
package backend_Models;

import app_Controller.Kaizen_85;
import com.sun.management.OperatingSystemMXBean;
import frontend_View.Settings;
import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author kell-gigabyte
 */
public class CpuMemoryPattern extends Pattern implements Runnable {

    private OperatingSystemMXBean bean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

    private ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

    private BigDecimal memFree;
    private BigDecimal memTotal;
    private BigDecimal Cpu;

    private final int MAXPIXEL = 1024; // the most that can be represented per segment

    public CpuMemoryPattern(Settings set, SerialComms serial) throws GeneralSettingsException {
        super(set, serial, 1, 0, 0, 0, 2);
        Kaizen_85.newEvent("CPU/MEM Pattern Set.");
        //System.out.println("CPUMEM STARTED");
    }

    @Override
    public void start() {
        this.ses.scheduleAtFixedRate(this, 0, 1, TimeUnit.SECONDS); // updates 2 times a second
        super.start();
    }

    @Override
    public void stop() {
        this.ses.shutdown();
    }

    @Override
    public void run() {
        //System.out.println("CPUMEM RUN");
        this.memTotal = new BigDecimal(bean.getTotalPhysicalMemorySize());
        this.memFree = new BigDecimal(bean.getFreePhysicalMemorySize());
        //System.out.println("Got after mem get");
        this.Cpu = new BigDecimal(bean.getSystemCpuLoad());
        //System.out.println("Got after cpu get");
        int CpuLoad = (int) (this.Cpu.doubleValue() * this.MAXPIXEL);
        int memLoad = (int) ((1 - (memFree.doubleValue() / memTotal.doubleValue())) * this.MAXPIXEL);
        //System.out.println("Got after int parsing");
        this.setUpdateDelay(CpuLoad, 0);
        this.setUpdateDelay(memLoad, 1);
        //System.out.println("Got after setting the ints");
        this.updatePattern();
        System.out.println("Calculated CPU: " + CpuLoad + ", and calculeted RAM: " + memLoad);
    }
}
