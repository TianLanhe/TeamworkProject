package teamwork.window;

import java.awt.*;
import javax.swing.*;

public class NewsTextWindow extends AbstractWindow {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  JFrame jframe;
  JTextField textField;
  JButton jButton;
  JButton jButton2;
  JButton jButton3;
  JTextArea content;
  JTextArea title;
  JPanel jPanel;
  JPanel jPanel3;
  JPanel jPanel4;
  
  
  //final int length = 1000;
  //final int width = 600;
  
  public NewsTextWindow(){
    
    init();
    regitstComponent();
    addListener();
    
    //setVisible(true);
    //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �رյ�Ĭ�ϲ������رմ���
    
    //setSize(length, width);
    //setLocationRelativeTo(null); // ���þ�����ʾ

  }
  
  @Override
  protected void addListener(){
    
  }
  @Override
  protected void regitstComponent() {
  
  }

  @Override
  protected void init() {
    //jframe = new JFrame("���ض�ͯ���ű���");
    jframe = this;
    textField = new JTextField("��ǩ",20);
    jButton = new JButton("���");
    jButton2 = new JButton("��һ��");
    jButton3 = new JButton("��һ��");
    content = new JTextArea("��������",5,20);
    title = new JTextArea("���ű���",5,5);
    jPanel = new JPanel(new BorderLayout(10,10));
    jPanel3 = new JPanel(new BorderLayout());
    jPanel4 = new JPanel(new BorderLayout());
    
    
    jframe.setLayout(new BorderLayout(10,2));
    jframe.add(jPanel3, BorderLayout.EAST);
    jframe.add(jPanel4, BorderLayout.WEST);
    jframe.add(jPanel, BorderLayout.NORTH);
    
    jPanel.add(jButton, BorderLayout.EAST);
    jPanel.add(textField, BorderLayout.CENTER);
    jPanel.add(new JScrollPane(title),BorderLayout.SOUTH);

    jPanel4.add(jButton2);
    jPanel3.add(jButton3);
    

    jframe.add(content, BorderLayout.CENTER);
    
    jframe.setVisible(true);
    FrameUtil2.initFram(jframe, 500, 700);
  }
 
  class FrameUtil
  {
      public void  initFram(JFrame f, int width, int height)
      {
          Toolkit toolkit = Toolkit.getDefaultToolkit();
          f.setVisible(true);
          f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          Dimension d = toolkit.getScreenSize();
          int x = (int)d.getWidth();
          int y = (int)d.getHeight();
          f.setBounds((x-width)/2, (y - height )/2, width, height);
      }
  }
}
