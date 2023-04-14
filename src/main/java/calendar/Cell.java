package calendar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Cell extends JButton {

    private Date date;
    private boolean title;
    private boolean isToDay;

    public Cell() {
        setContentAreaFilled(false);
        setBorder(null);
        setHorizontalAlignment(JLabel.CENTER);
    }

    public void asTitle() {
        title = true;
    }

    public boolean isTitle() {
        return title;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void currentMonth(boolean act) {
        if (act) {
            setForeground(new Color(68, 68, 68));
        } else {
            setForeground(new Color(169, 169, 169));
        }
    }

    public void setAsToDay(boolean isToday) {
        this.isToDay = isToday;
        if(isToday)
            setForeground(Color.WHITE);
    }
    
    private void drawCircle(Graphics2D g2, int x, int y, int diameter, Color color) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);
        g2.fillOval(x, y, diameter, diameter);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        if (title) {
            grphcs.setColor(new Color(213, 213, 213));
            grphcs.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
        }
        if (isToDay) {
            Graphics2D g2 = (Graphics2D) grphcs;
            int diameter = 20;
            int x = (getWidth() - diameter) / 2;
            int y = (getHeight() - diameter) / 2;
            drawCircle(g2, x, y, diameter, new Color(101, 122, 240));
            //g2.fillRoundRect(x, y, 30, 30, 100, 100);
        }
        super.paintComponent(grphcs);
    }

}
