import javax.swing.*;
import java.awt.*;

public class CustomProgressBar {
    //https://stackoverflow.com/questions/24379251/change-ui-lookup-for-progressbar-swing-in-nimbus-theme

    private CustomProgressBar(){

    }

    public static JProgressBar set(JProgressBar progressBar){

        UIDefaults defaults = new UIDefaults();
        ProgressPainter painter1 = new ProgressPainter(Color.WHITE, Color.GREEN);
        ProgressPainter painter2 = new ProgressPainter(Color.WHITE, Color.YELLOW);
        ProgressPainter painter3 = new ProgressPainter(Color.WHITE, Color.RED);

        if(progressBar.getValue() <= 50){
            defaults.put("ProgressBar[Enabled].foregroundPainter", painter1);
            defaults.put("ProgressBar[Enabled+Finished].foregroundPainter", painter1);
            progressBar.putClientProperty("Nimbus.Overrides.InheritDefaults", Boolean.TRUE);
            progressBar.putClientProperty("Nimbus.Overrides", defaults);
        }
        else if(progressBar.getValue() > 50 && progressBar.getValue() < 80){
            defaults.put("ProgressBar[Enabled].foregroundPainter", painter2);
            defaults.put("ProgressBar[Enabled+Finished].foregroundPainter", painter2);
            progressBar.putClientProperty("Nimbus.Overrides.InheritDefaults", Boolean.TRUE);
            progressBar.putClientProperty("Nimbus.Overrides", defaults);
        }
        else{
            defaults.put("ProgressBar[Enabled].foregroundPainter", painter3);
            defaults.put("ProgressBar[Enabled+Finished].foregroundPainter", painter3);
            progressBar.putClientProperty("Nimbus.Overrides.InheritDefaults", Boolean.TRUE);
            progressBar.putClientProperty("Nimbus.Overrides", defaults);
        }

        return progressBar;
    }

}

class ProgressPainter implements Painter {

    private Color light, dark;
    private GradientPaint gradPaint;

    public ProgressPainter(Color light, Color dark) {
        this.light = light;
        this.dark = dark;
    }

    @Override
    public void paint(Graphics2D g, Object c, int w, int h) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gradPaint = new GradientPaint((w / 2.0f), 0, light, (w / 2.0f), (h / 2.0f), dark, true);
        g.setPaint(gradPaint);
        g.fillRect(2, 2, (w - 5), (h - 5));

        Color outline = new Color(0, 85, 0);
        g.setColor(outline);
        g.drawRect(2, 2, (w - 5), (h - 5));
        Color trans = new Color(outline.getRed(), outline.getGreen(), outline.getBlue(), 100);
        g.setColor(trans);
        g.drawRect(1, 1, (w - 3), (h - 3));
    }
}
