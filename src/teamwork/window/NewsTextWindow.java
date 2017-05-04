package teamwork.window;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import teamwork.listener.ChangeNewsListener;
import teamwork.model.News;
import teamwork.r.R;

public class NewsTextWindow extends AbstractWindow {

  private static final long serialVersionUID = 1L;

  private JTextField title; // ����
  private JTextField source;// ��Դ
  private JTextField date;// ����
  private JTextField type;// ����

  private JTextArea content;// ����

  private JButton prevNews;// ��һ��
  private JButton nextNews;// ��һ��

  private JTree parentTree;

  private News news;

  public NewsTextWindow(News news) {
    this.news = news;
  }

  private void showNewsDetails() {
    title.setText(news.getTitle());
    source.setText(news.getLocation());
    date.setText(news.getDate());
    type.setText(news.getType());

    if (!news.update()) {
      JOptionPane.showMessageDialog(this, "������ȡ��������ʧ�ܣ��������������", "����", JOptionPane.WARNING_MESSAGE);
    } else {
      content.setText(news.getContent());
      content.setCaretPosition(0);// ������ƶ�����ͷ
    }
  }

  public void changeNews(News news) {
    this.news = news;
    showNewsDetails();
  }

  @Override
  protected void addListener() {
    ChangeNewsListener changeListener = new ChangeNewsListener(news);
    nextNews.addActionListener(changeListener);
    prevNews.addActionListener(changeListener);
  }

  @Override
  protected void regitstComponent() {
    R r = R.getInstance();
    r.registObject("NewsTextWindow", this);
  }

  @Override
  protected void init() {
    Font font = new Font("����", 0, 15);

    JPanel newsDetailPane = new JPanel();
    newsDetailPane.setLayout(null);
    newsDetailPane.setBounds(0, 0, 800, 600);
    add(newsDetailPane);

    title = new JTextField("���ű���");
    title.setFont(new Font("����", 1, 20));
    title.setHorizontalAlignment(JTextField.CENTER);
    title.setBounds(40, 10, 720, 40);
    newsDetailPane.add(title);

    JLabel locationLabel = new JLabel("��Դ��");
    locationLabel.setFont(font);
    locationLabel.setBounds(40, 55, 50, 40);
    newsDetailPane.add(locationLabel);

    source = new JTextField("��Դ��վ");
    source.setBounds(90, 60, 120, 30);
    newsDetailPane.add(source);

    JLabel dateLabel = new JLabel("���ڣ�");
    dateLabel.setFont(font);
    dateLabel.setBounds(340, 55, 50, 40);
    newsDetailPane.add(dateLabel);

    date = new JTextField("����");
    date.setBounds(390, 60, 120, 30);
    newsDetailPane.add(date);

    JLabel typeLabel = new JLabel("���ͣ�");
    typeLabel.setFont(font);
    typeLabel.setBounds(590, 55, 50, 40);
    newsDetailPane.add(typeLabel);

    type = new JTextField("����");
    type.setBounds(640, 60, 120, 30);
    newsDetailPane.add(type);

    content = new JTextArea("", 20, 50);
    content.setLineWrap(true);
    JScrollPane contentPane = new JScrollPane(content);
    contentPane.setBounds(40, 100, 720, 460);
    newsDetailPane.add(contentPane);

    prevNews = new JButton("prev");
    prevNews.setBounds(0, 300, 40, 60);
    prevNews.setIcon(new ImageIcon("res/lastOne.png"));
    newsDetailPane.add(prevNews);

    nextNews = new JButton("next");
    nextNews.setBounds(760, 300, 40, 60);
    nextNews.setIcon(new ImageIcon("res/nextOne.png"));
    newsDetailPane.add(nextNews);

    title.setEditable(false);
    source.setEditable(false);
    date.setEditable(false);
    type.setEditable(false);
    content.setEditable(false);

    JPanel labelPane = new JPanel();
    labelPane.setBounds(810, 0, 200, 600);
    labelPane.setLayout(null);
    add(labelPane);

    String[] labels = {"���뵳��", "�ظ�����д", "��������ذ�", "��������������Ŀ", "��Ὠ���뿴��", "����Ҹ�", "��������", "��������"};
    JList<String> label = new JList<String>(labels);
    label.setSize(10, 5);
    JScrollPane labelsPlayPane = new JScrollPane(label);
    labelsPlayPane.setBounds(0, 30, 180, 180);
    labelPane.add(labelsPlayPane);

    JLabel tagLabel = new JLabel("��ѡ��ǩ��");
    tagLabel.setBounds(0, 10, 70, 20);
    labelPane.add(tagLabel);

    JButton addLabel = new JButton("���");
    addLabel.setBounds(20, 220, 60, 30);
    labelPane.add(addLabel);

    JButton deleteLabel = new JButton("ɾ��");
    labelPane.add(deleteLabel);
    deleteLabel.setBounds(100, 220, 60, 30);

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
    treePane.setBounds(0, 260, 180, 300);
    labelPane.add(treePane);

    showNewsDetails();

  }

  @Override
  protected void initWindow() {
    super.initWindow();
    setTitle("���ض�ͯ���ű���");

    setLayout(null);
  }

}
