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
 * @Broken, not completed due to brain dying out due to the maths involved
 * @author kell-gigabyte
 */
public class CpuMemoryPatternAdvanced extends Pattern implements Runnable {

    private OperatingSystemMXBean bean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

    private ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

    private BigDecimal memFree;
    private BigDecimal memTotal;
    private BigDecimal Cpu;

    private int totalPixels;
    private int CpuPixels;
    private int MemPixels;
    private final int MAXPIXEL = (255 + 255 + 255); // the most that can be represented per pixel
    private final int MAXVALUECPU;
    private final int MAXVALUERAM;

    public CpuMemoryPatternAdvanced(Settings set, SerialComms serial) throws GeneralSettingsException {
        super(set, serial, 2, 0, 0, 2, 0);
        Kaizen_85.newEvent("CPU/MEM Pattern Set.");
        this.totalPixels = (set.getStripWidth() * set.getStripWidth());
        this.CpuPixels = (this.totalPixels / 2) - 1;
        this.MemPixels = this.totalPixels - this.CpuPixels;
        this.MAXVALUECPU = this.MAXPIXEL * this.CpuPixels;
        this.MAXVALUERAM = this.MAXPIXEL * this.MemPixels;
        System.out.println("Max number for CPU: " + this.MAXVALUECPU + ", Max value for RAM: " + this.MAXVALUERAM);
    }

    @Override
    public void start() {
        this.ses.scheduleAtFixedRate(this, 0, 333, TimeUnit.MILLISECONDS); // updates 3 times a second
        super.start();
    }

    @Override
    public void stop() {
        this.ses.shutdown();
    }

    @Override
    public void run() {
        int cpuRed, cpuGreen, cpuBlue, memRed, memGreen, memBlue;
        this.memTotal = new BigDecimal(bean.getTotalPhysicalMemorySize());
        this.memFree = new BigDecimal(bean.getFreePhysicalMemorySize());
        this.Cpu = new BigDecimal(bean.getSystemCpuLoad());
        int CpuLoad = (int) (this.Cpu.floatValue() * this.MAXVALUECPU);
        int memLoad = (int) ((this.memFree.doubleValue() / this.memTotal.doubleValue()) * this.MAXVALUERAM);
        cpuRed = ((CpuLoad >> 16) & 0xFF);
        // this.setUpdateColor(CpuLoad, new Color(cpuRed, cpuGreen, cpuBlue));
        // this.setUpdateColor(memLoad, new Color(memRed, memGreen, memBlue));
        this.updatePattern();
        System.out.println("Calculated CPU: " + CpuLoad + ", and calculeted RAM: " + memLoad);
        //System.out.println("Percentages: CPU " + CpuLoad / 655.36 + "% Mem " + memLoad / 655.36 + "%");
    }
}
