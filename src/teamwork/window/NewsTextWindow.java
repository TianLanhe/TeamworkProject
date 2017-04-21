package teamwork.window;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import teamwork.r.R;

public class NewsTextWindow extends AbstractWindow {

  private static final long serialVersionUID = 1L;

  JTextField title;
  JTextField source;
  JTextField author;
  JTextField date;
  JTextField type;
  JTextArea content;
  JButton lastNews;
  JButton nextNews;
  JPanel newsPane;
  JTree parentTree;

  @Override
  protected void addListener() {
    R r = R.getInstance();
    r.registObject("newsTextWindow_textField_title", title);
    r.registObject("newsTextWindow_textArea_content", content);
  }

  @Override
  protected void regitstComponent() {

  }

  @Override
  protected void init() {

    title = new JTextField("���ű���");
    source = new JTextField("��Դ��վ");
    author = new JTextField("����");
    date = new JTextField("����");
    type = new JTextField("���");
    content = new JTextArea("��������", 20, 1000);
    content.setLineWrap(true);
    JScrollPane contentPane = new JScrollPane(content);
    lastNews = new JButton();
    nextNews = new JButton();
    newsPane = new JPanel();

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
    title.setBounds(200, 0, 380, 40);
    title.setFont(new Font("����", Font.BOLD, 20));
    source.setBounds(200, 50, 80, 30);
    author.setBounds(300, 50, 80, 30);
    date.setBounds(400, 50, 80, 30);
    type.setBounds(500, 50, 80, 30);
    contentPane.setBounds(40, 100, 720, 600);
    lastNews.setBounds(0, 300, 40, 60);
    lastNews.setIcon(new ImageIcon("res/lastOne.png"));
    nextNews.setBounds(760, 300, 40, 60);
    nextNews.setIcon(new ImageIcon("res/nextOne.png"));

    JPanel labelPane = new JPanel();
    labelPane.setBounds(800, 0, 200, 600);
    labelPane.setLayout(null);
    getContentPane().add(labelPane);

    JLabel tagLabel = new JLabel("��ѡ��ǩ��");
    tagLabel.setBounds(800, 0, 70, 20);
    labelPane.add(tagLabel);

    JTextArea label = new JTextArea();
    labelPane.add(label);
    label.setBounds(800, 20, 180, 180);

    JButton addLabel = new JButton("���");
    labelPane.add(addLabel);
    addLabel.setBounds(820, 210, 60, 30);

    JButton deleteLabel = new JButton("ɾ��");
    labelPane.add(deleteLabel);
    deleteLabel.setBounds(900, 210, 60, 30);

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

    parentTree = new JTree(allLabel);
    JScrollPane treePane = new JScrollPane(parentTree);
    labelPane.add(treePane);
    treePane.setBounds(800, 250, 200, 300);
    
    tagLabel.repaint();
    label.repaint();
    this.validate();
    
  }

  @Override
  protected void initWindow() {
    super.initWindow();
    setTitle("���ض�ͯ���ű���");
    setSize(1000, 600);
    setLocationRelativeTo(null); // ���þ�����ʾ
  }

}
