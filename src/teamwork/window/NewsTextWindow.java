package teamwork.window;

import java.awt.Font;
import java.util.List;

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

import teamwork.listener.ChangeNewsListener;
import teamwork.listener.PostTagListener;
import teamwork.listener.TearTagListener;
import teamwork.model.ClassCatalog;
import teamwork.model.News;
import teamwork.model.viewmodel.NewsTreeModel;
import teamwork.model.viewmodel.TagListModel;
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

  private JButton addLabel;// ��ӱ�ǩ
  private JButton deleteLabel;// ɾ����ǩ

  private JList<String> tagsList;

  private JTree parentTree;

  private News news;
  private List<News> newsList;

  public NewsTextWindow(News news, List<News> newsList) {
    this.news = news;
    this.newsList = newsList;
  }

  private void showNewsDetails() {
    title.setText(news.getTitle());
    source.setText(news.getLocation());
    date.setText(news.getDate());
    type.setText(news.getType());

    if (news.getContent().isEmpty() && !news.update()) {
      JOptionPane.showMessageDialog(this, "������ȡ��������ʧ�ܣ��������������", "����", JOptionPane.WARNING_MESSAGE);
    }
    content.setText(news.getContent());
    content.setCaretPosition(0);// ������ƶ�����ͷ

    ((TagListModel) tagsList.getModel()).setListData(news.getTagsList());
  }

  public void changeNews(News news) {
    this.news = news;
    showNewsDetails();
  }

  public News getNews() {
    return news;
  }

  @Override
  protected void addListener() {
    ChangeNewsListener changeListener = new ChangeNewsListener(newsList);
    nextNews.addActionListener(changeListener);
    prevNews.addActionListener(changeListener);

    addLabel.addActionListener(new PostTagListener());
    deleteLabel.addActionListener(new TearTagListener());
  }

  @Override
  protected void regitstComponent() {
    R r = R.getInstance();
    r.registObject("NewsTextWindow", this);
    r.registObject("tagsTree", parentTree);
    r.registObject("tagsList", tagsList);
  }

  @Override
  protected void init() {
    Font font = new Font("����", 0, 15);

    JPanel newsDetailPane = new JPanel();
    newsDetailPane.setLayout(null);
    newsDetailPane.setBounds(5, 0, 800, 600);
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
    source.setFont(font);
    source.setBounds(90, 60, 120, 30);
    newsDetailPane.add(source);

    JLabel dateLabel = new JLabel("���ڣ�");
    dateLabel.setFont(font);
    dateLabel.setBounds(340, 55, 50, 40);
    newsDetailPane.add(dateLabel);

    date = new JTextField("����");
    date.setFont(font);
    date.setBounds(390, 60, 120, 30);
    newsDetailPane.add(date);

    JLabel typeLabel = new JLabel("���ͣ�");
    typeLabel.setFont(font);
    typeLabel.setBounds(590, 55, 50, 40);
    newsDetailPane.add(typeLabel);

    type = new JTextField("����");
    type.setFont(font);
    type.setBounds(640, 60, 120, 30);
    newsDetailPane.add(type);

    content = new JTextArea("", 20, 50);
    content.setFont(font);
    content.setLineWrap(true);// �Զ�����
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

    JLabel tagLabel = new JLabel("��ѡ��ǩ��");
    tagLabel.setFont(font);
    tagLabel.setBounds(0, 10, 100, 20);
    labelPane.add(tagLabel);

    tagsList = new JList<String>(new TagListModel());
    JScrollPane labelsPlayPane = new JScrollPane(tagsList);
    tagsList.setFont(font);
    // tagsList.setFixedCellHeight(20);
    labelsPlayPane.setBounds(0, 30, 180, 180);
    labelPane.add(labelsPlayPane);

    addLabel = new JButton("���");
    addLabel.setFont(font);
    addLabel.setBounds(0, 220, 80, 30);
    labelPane.add(addLabel);

    deleteLabel = new JButton("ɾ��");
    deleteLabel.setFont(font);
    labelPane.add(deleteLabel);
    deleteLabel.setBounds(100, 220, 80, 30);

    NewsTreeModel model = new NewsTreeModel(ClassCatalog.getInstance());
    parentTree = new JTree(model);
    parentTree.setFont(font);
    parentTree.setRowHeight(20);// �������
    parentTree.setRootVisible(false);// ���ڵ㲻�ɼ�
    parentTree.setToggleClickCount(1);// ����һ��չ���ڵ�
    parentTree.setFont(new Font("����", 0, 14));

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
