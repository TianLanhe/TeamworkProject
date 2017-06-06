package teamwork.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import teamwork.window.StatisticsWindow;

public class StatisticsListener implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent arg0) {
    new StatisticsWindow().run();
  }

}
