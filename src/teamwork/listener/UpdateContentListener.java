package teamwork.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JOptionPane;


import teamwork.model.NewsCatalog;



public class UpdateContentListener implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent arg0) {
    
    NewsCatalog newsCatalog = NewsCatalog.getInstance();
    int num = newsCatalog.updateAll();
    String message = "共 " + newsCatalog.size() + " 条新闻\n成功更新 " + num + " 条新闻";
    JOptionPane.showMessageDialog(null, message, "更新成功", JOptionPane.INFORMATION_MESSAGE);
    
    
  }
}
