package teamwork.window;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import teamwork.listener.DoTestListener;
import teamwork.listener.ListDoubleClickListener;
import teamwork.listener.SaveTestListener;
import teamwork.model.News;
import teamwork.model.viewmodel.NewsListModel;

public class TestWindow extends AbstractWindow {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private JButton myTestButton;
  private JButton saveTestButton;
  private JButton doTestButton;
  
  private JList<News> newsList;
  
  @Override
  protected void addListener() {
    newsList.addMouseListener(new ListDoubleClickListener());
    
    saveTestButton.addActionListener(new SaveTestListener());
    doTestButton.addActionListener(new DoTestListener());
  }

  @Override
  protected void regitstComponent() {
    // TODO Auto-generated method stub

  }

  @Override
  protected void init() {
    Font font = new Font("����", 1, 20);

    JPanel testPanel = new JPanel();
    testPanel.setLayout(null);
    
    myTestButton = new JButton("�ҵĲ���");
    myTestButton.setFont(font);
    saveTestButton = new JButton("�������");
    saveTestButton.setFont(font);
    doTestButton = new JButton("���в���");
    doTestButton.setFont(font);

    myTestButton.setBounds(160,20,120,40);
    saveTestButton.setBounds(340,20,120,40);
    doTestButton.setBounds(520,20,120,40);
    
    testPanel.add(myTestButton);
    testPanel.add(saveTestButton);
    testPanel.add(doTestButton);
    
    testPanel.setBounds(0, 0, 800, 60);
    add(testPanel);

    JLabel label = new JLabel("�����б���");
    label.setFont(font);
    
    newsList = new JList<News>(new NewsListModel<News>());
    JScrollPane scrollPane = new JScrollPane(newsList);
    newsList.setFont(new Font("����", 1, 14));// ��������
    newsList.setFixedCellHeight(24);// �������
    
    add(label);
    add(scrollPane);

    label.setBounds(80, 70, 160, 30);
    scrollPane.setBounds(80, 110, 640, 380);

  }

  @Override
  protected void initWindow() {
    super.initWindow();

    setTitle("���ض�ͯ�����������");
    setSize(800,540);
    setLayout(null);
    setLocationRelativeTo(null);
  }
}