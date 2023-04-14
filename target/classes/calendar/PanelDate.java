package calendar;
import java.awt.Component;
import java.util.Calendar;
import java.util.Date;

public class PanelDate extends javax.swing.JLayeredPane {

    private int month;
    private int year;

     public PanelDate()
     {
        initComponents();
     }
    
    public PanelDate(int month, int year) {
        initComponents();
        this.month = month;
        this.year = year;
        init();
    }
    
    public void Reset(int month, int year)
    {
        this.month = month;
        this.year = year;
        init();
        System.out.println(month + " - " + year + "test");
        repaint();
    }

    private void init() {
        mon.asTitle();
        tue.asTitle();
        wed.asTitle();
        thu.asTitle();
        fri.asTitle();
        sat.asTitle();
        sun.asTitle();
        setDate();
    }

    private void setDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);  //  month jan as 0 so start from 0
        calendar.set(Calendar.DATE, 1);
        int startDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;  //  get day of week -1 to index
        calendar.add(Calendar.DATE, -startDay);
        ToDay toDay = getToDay();
        for (Component com : getComponents()) {
            Cell cell = (Cell) com;
            if (!cell.isTitle()) {
                cell.setText(calendar.get(Calendar.DATE) + "");
                cell.setDate(calendar.getTime());
                cell.currentMonth(calendar.get(Calendar.MONTH) == month - 1);
                if (toDay.isToDay(new ToDay(calendar.get(Calendar.DATE), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR)))) {
                    cell.setAsToDay(true);
                }
                else
                {
                    cell.setAsToDay(false);
                }
                calendar.add(Calendar.DATE, 1); //  up 1 day
            }
        }
    }

    private ToDay getToDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return new ToDay(calendar.get(Calendar.DATE), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sun = new calendar.Cell();
        mon = new calendar.Cell();
        tue = new calendar.Cell();
        wed = new calendar.Cell();
        thu = new calendar.Cell();
        fri = new calendar.Cell();
        sat = new calendar.Cell();
        cell8 = new calendar.Cell();
        cell9 = new calendar.Cell();
        cell10 = new calendar.Cell();
        cell11 = new calendar.Cell();
        cell12 = new calendar.Cell();
        cell13 = new calendar.Cell();
        cell14 = new calendar.Cell();
        cell15 = new calendar.Cell();
        cell16 = new calendar.Cell();
        cell17 = new calendar.Cell();
        cell18 = new calendar.Cell();
        cell19 = new calendar.Cell();
        cell20 = new calendar.Cell();
        cell21 = new calendar.Cell();
        cell22 = new calendar.Cell();
        cell23 = new calendar.Cell();
        cell24 = new calendar.Cell();
        cell25 = new calendar.Cell();
        cell26 = new calendar.Cell();
        cell27 = new calendar.Cell();
        cell28 = new calendar.Cell();
        cell29 = new calendar.Cell();
        cell30 = new calendar.Cell();
        cell31 = new calendar.Cell();
        cell32 = new calendar.Cell();
        cell33 = new calendar.Cell();
        cell34 = new calendar.Cell();
        cell35 = new calendar.Cell();
        cell36 = new calendar.Cell();
        cell37 = new calendar.Cell();
        cell38 = new calendar.Cell();
        cell39 = new calendar.Cell();
        cell40 = new calendar.Cell();
        cell41 = new calendar.Cell();
        cell42 = new calendar.Cell();
        cell43 = new calendar.Cell();
        cell44 = new calendar.Cell();
        cell45 = new calendar.Cell();
        cell46 = new calendar.Cell();
        cell47 = new calendar.Cell();
        cell48 = new calendar.Cell();
        cell49 = new calendar.Cell();

        setLayout(new java.awt.GridLayout(7, 7));

        sun.setText("Sun");
        sun.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        add(sun);

        mon.setText("Mon");
        mon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        add(mon);

        tue.setText("Tue");
        tue.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        add(tue);

        wed.setText("Wed");
        wed.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        add(wed);

        thu.setText("Thu");
        thu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        add(thu);

        fri.setText("Fri");
        fri.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        add(fri);

        sat.setText("Sat");
        sat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        add(sat);
        add(cell8);
        add(cell9);
        add(cell10);
        add(cell11);
        add(cell12);
        add(cell13);
        add(cell14);
        add(cell15);
        add(cell16);
        add(cell17);
        add(cell18);
        add(cell19);
        add(cell20);
        add(cell21);
        add(cell22);
        add(cell23);
        add(cell24);
        add(cell25);
        add(cell26);
        add(cell27);
        add(cell28);
        add(cell29);
        add(cell30);
        add(cell31);
        add(cell32);
        add(cell33);
        add(cell34);
        add(cell35);
        add(cell36);
        add(cell37);
        add(cell38);
        add(cell39);
        add(cell40);
        add(cell41);
        add(cell42);
        add(cell43);
        add(cell44);
        add(cell45);
        add(cell46);
        add(cell47);
        add(cell48);
        add(cell49);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private calendar.Cell cell10;
    private calendar.Cell cell11;
    private calendar.Cell cell12;
    private calendar.Cell cell13;
    private calendar.Cell cell14;
    private calendar.Cell cell15;
    private calendar.Cell cell16;
    private calendar.Cell cell17;
    private calendar.Cell cell18;
    private calendar.Cell cell19;
    private calendar.Cell cell20;
    private calendar.Cell cell21;
    private calendar.Cell cell22;
    private calendar.Cell cell23;
    private calendar.Cell cell24;
    private calendar.Cell cell25;
    private calendar.Cell cell26;
    private calendar.Cell cell27;
    private calendar.Cell cell28;
    private calendar.Cell cell29;
    private calendar.Cell cell30;
    private calendar.Cell cell31;
    private calendar.Cell cell32;
    private calendar.Cell cell33;
    private calendar.Cell cell34;
    private calendar.Cell cell35;
    private calendar.Cell cell36;
    private calendar.Cell cell37;
    private calendar.Cell cell38;
    private calendar.Cell cell39;
    private calendar.Cell cell40;
    private calendar.Cell cell41;
    private calendar.Cell cell42;
    private calendar.Cell cell43;
    private calendar.Cell cell44;
    private calendar.Cell cell45;
    private calendar.Cell cell46;
    private calendar.Cell cell47;
    private calendar.Cell cell48;
    private calendar.Cell cell49;
    private calendar.Cell cell8;
    private calendar.Cell cell9;
    private calendar.Cell fri;
    private calendar.Cell mon;
    private calendar.Cell sat;
    private calendar.Cell sun;
    private calendar.Cell thu;
    private calendar.Cell tue;
    private calendar.Cell wed;
    // End of variables declaration//GEN-END:variables
}
