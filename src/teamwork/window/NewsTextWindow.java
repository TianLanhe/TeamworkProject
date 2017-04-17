package teamwork.window;

import java.awt.*;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class NewsTextWindow extends AbstractWindow {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  @Override
  protected void addListener(){
    
  }
  @Override
  protected void regitstComponent() {
  
  }

  @Override
  protected void init() {
    
    JTextField title = new JTextField("���ű���");
    JTextField source = new JTextField("��Դ��վ");
    JTextField author = new JTextField("����");
    JTextField date = new JTextField("����");
    JTextField type = new JTextField("���");
    JTextArea content = new JTextArea("��������",20,1000);
    JScrollPane contentPane = new JScrollPane(content);
    JButton lastNews = new JButton();
    JButton nextNews = new JButton();   
    JPanel newsPane = new JPanel();
      
    newsPane.setLayout(null);
    
    getContentPane().add(newsPane); 
    newsPane.add(title);
    newsPane.add(source);
    newsPane.add(author);
    newsPane.add(date);
    newsPane.add(type);
    newsPane.add(contentPane);
    newsPane.add(lastNews);
    newsPane.add(nextNews);

    
    newsPane.setBounds(0, 0, 800, 600);
    title.setBounds(200,0,380,40);
    title.setFont(new Font("����",Font.BOLD,20));
    source.setBounds(200, 50, 80, 30);
    author.setBounds(300, 50, 80, 30);
    date.setBounds(400, 50, 80, 30);
    type.setBounds(500, 50, 80, 30);
    contentPane.setBounds(40, 100, 720, 600);
    lastNews.setBounds(0, 300, 40, 60);
    lastNews.setIcon(new ImageIcon(NewsTextWindow.class
      .getResource("/img/lastOne.png")));
    nextNews.setBounds(760, 300, 40, 60);
    nextNews.setIcon(new ImageIcon(NewsTextWindow.class
      .getResource("/img/nextOne.png")));
    
    JPanel labelPane = new JPanel();
    labelPane.setBounds(800, 0, 200, 600);
    labelPane.setLayout(null);
    getContentPane().add(labelPane);
    
    JLabel tagLabel = new JLabel("��ѡ��ǩ��");
    tagLabel.setBounds(800, 0, 70, 20);
    labelPane.add(tagLabel);
    
    JTextArea label = new JTextArea();
    label.setBounds(800, 20, 180, 180);
    labelPane.add(label);
    
    JButton addLabel = new JButton("���");
    addLabel.setBounds(820, 210, 60, 30);
    labelPane.add(addLabel);
    
    JButton deleteLabel = new JButton("ɾ��");
    deleteLabel.setBounds(900, 210, 60, 30);
    labelPane.add(deleteLabel);
    
    DefaultMutableTreeNode newsRank = new DefaultMutableTreeNode("�������");
    newsRank.add(new DefaultMutableTreeNode("���뵳��"));
    newsRank.add(new DefaultMutableTreeNode("ʡ������"));
    newsRank.add(new DefaultMutableTreeNode("���е���"));
    
    
    DefaultMutableTreeNode newsType = new DefaultMutableTreeNode("��������");
    newsType.add(new DefaultMutableTreeNode("��������"));
    newsType.add(new DefaultMutableTreeNode("�ظ�����д"));
    newsType.add(new DefaultMutableTreeNode("����"));
    newsType.add(new DefaultMutableTreeNode("����"));
     
    DefaultMutableTreeNode helpType = new DefaultMutableTreeNode("��������ذ�");
    helpType.add(new DefaultMutableTreeNode("����һ�ξ�����"));
    helpType.add(new DefaultMutableTreeNode("���λ���ŵ���Ŀ֮һ"));
    helpType.add(new DefaultMutableTreeNode("��ѿ���"));
    helpType.add(new DefaultMutableTreeNode("��������������Ŀ"));
    helpType.add(new DefaultMutableTreeNode("����"));
    
    DefaultMutableTreeNode newsTheme = new DefaultMutableTreeNode("��������");
    newsTheme.add(helpType);
    newsTheme.add(new DefaultMutableTreeNode("��Ὠ���뿴��"));
    newsTheme.add(new DefaultMutableTreeNode("���õ�λ�����"));
    newsTheme.add(new DefaultMutableTreeNode("���ض�ͯ���ܱ���Ź��"));
    newsTheme.add(new DefaultMutableTreeNode("���ض�ͯ�������ֵ�"));
    newsTheme.add(new DefaultMutableTreeNode("���ض�ͯ������æ"));
    newsTheme.add(new DefaultMutableTreeNode("���ض�ͯŬ���Ͻ�"));
    newsTheme.add(new DefaultMutableTreeNode("��ĸ�ڳ��еļ�������"));
    newsTheme.add(new DefaultMutableTreeNode("����"));
    
    DefaultMutableTreeNode helpSponsor = new DefaultMutableTreeNode("����������");
    helpSponsor.add(new DefaultMutableTreeNode("��������"));
    helpSponsor.add(new DefaultMutableTreeNode("��ҵ"));
    helpSponsor.add(new DefaultMutableTreeNode("��ҵ��λ"));
    helpSponsor.add(new DefaultMutableTreeNode("��������"));
    helpSponsor.add(new DefaultMutableTreeNode("����"));
    
    DefaultMutableTreeNode awardBody = new DefaultMutableTreeNode("��������");
    awardBody.add(new DefaultMutableTreeNode("��������"));
    awardBody.add(new DefaultMutableTreeNode("��ҵ"));
    awardBody.add(new DefaultMutableTreeNode("��ҵ��λ"));
    awardBody.add(new DefaultMutableTreeNode("��������"));
    awardBody.add(new DefaultMutableTreeNode("����"));

    DefaultMutableTreeNode newsImage = new DefaultMutableTreeNode("��������");
    newsImage.add(new DefaultMutableTreeNode("��������"));
    newsImage.add(new DefaultMutableTreeNode("��������"));
    newsImage.add(new DefaultMutableTreeNode("����Ҹ�"));
    newsImage.add(new DefaultMutableTreeNode("�����ͯ"));
    newsImage.add(new DefaultMutableTreeNode("����"));
    
    DefaultMutableTreeNode allLabel = new DefaultMutableTreeNode("��ѡ��ǩ");
    allLabel.add(newsRank);
    allLabel.add(newsType);
    allLabel.add(newsTheme);
    allLabel.add(helpSponsor);
    allLabel.add(awardBody);
    allLabel.add(newsImage);

    final JTree parentTree = new JTree(allLabel);
    JScrollPane treePane = new JScrollPane(parentTree);
    treePane.setBounds(800, 250, 200, 300);
    labelPane.add(treePane);
  }

  @Override
  protected void initWindow(){
    setTitle("���ض�ͯ���ű���");
    setSize(1000, 600);
    setLocationRelativeTo(null); // ���þ�����ʾ
    setVisible(true);// Ĭ�Ͽɼ�
    setResizable(false);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// �رյ�Ĭ�ϲ������رմ���
  }
 
}
