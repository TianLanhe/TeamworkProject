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
    String message = "�� " + newsCatalog.size() + " ������\n�ɹ����� " + num + " ������";
    JOptionPane.showMessageDialog(null, message, "���³ɹ�", JOptionPane.INFORMATION_MESSAGE);
    
    
  }
}
