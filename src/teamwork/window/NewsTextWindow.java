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

  private JTextField title; // 标题
  private JTextField source;// 来源
  private JTextField date;// 日期
  private JTextField type;// 类型

  private JTextArea content;// 内容

  private JButton prevNews;// 上一条
  private JButton nextNews;// 下一条

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
      JOptionPane.showMessageDialog(this, "联网获取新闻内容失败，请检查网络或链接", "警告", JOptionPane.WARNING_MESSAGE);
    } else {
      content.setText(news.getContent());
      content.setCaretPosition(0);// 将光标移动到开头
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
    Font font = new Font("宋体", 0, 15);

    JPanel newsDetailPane = new JPanel();
    newsDetailPane.setLayout(null);
    newsDetailPane.setBounds(0, 0, 800, 600);
    add(newsDetailPane);

    title = new JTextField("新闻标题");
    title.setFont(new Font("宋体", 1, 20));
    title.setHorizontalAlignment(JTextField.CENTER);
    title.setBounds(40, 10, 720, 40);
    newsDetailPane.add(title);

    JLabel locationLabel = new JLabel("来源：");
    locationLabel.setFont(font);
    locationLabel.setBounds(40, 55, 50, 40);
    newsDetailPane.add(locationLabel);

    source = new JTextField("来源网站");
    source.setBounds(90, 60, 120, 30);
    newsDetailPane.add(source);

    JLabel dateLabel = new JLabel("日期：");
    dateLabel.setFont(font);
    dateLabel.setBounds(340, 55, 50, 40);
    newsDetailPane.add(dateLabel);

    date = new JTextField("日期");
    date.setBounds(390, 60, 120, 30);
    newsDetailPane.add(date);

    JLabel typeLabel = new JLabel("类型：");
    typeLabel.setFont(font);
    typeLabel.setBounds(590, 55, 50, 40);
    newsDetailPane.add(typeLabel);

    type = new JTextField("类型");
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

    String[] labels = {"中央党报", "特稿与特写", "社会帮助与关爱", "设立长期资助项目", "社会建议与看法", "沐恩幸福", "政府部门", "政府部门"};
    JList<String> label = new JList<String>(labels);
    label.setSize(10, 5);
    JScrollPane labelsPlayPane = new JScrollPane(label);
    labelsPlayPane.setBounds(0, 30, 180, 180);
    labelPane.add(labelsPlayPane);

    JLabel tagLabel = new JLabel("已选标签：");
    tagLabel.setBounds(0, 10, 70, 20);
    labelPane.add(tagLabel);

    JButton addLabel = new JButton("添加");
    addLabel.setBounds(20, 220, 60, 30);
    labelPane.add(addLabel);

    JButton deleteLabel = new JButton("删除");
    labelPane.add(deleteLabel);
    deleteLabel.setBounds(100, 220, 60, 30);

    DefaultMutableTreeNode newsRank = new DefaultMutableTreeNode("新闻类别");
    newsRank.add(new DefaultMutableTreeNode("中央党报"));
    newsRank.add(new DefaultMutableTreeNode("省级党报"));
    newsRank.add(new DefaultMutableTreeNode("都市党报"));


    DefaultMutableTreeNode newsType = new DefaultMutableTreeNode("新闻类型");
    newsType.add(new DefaultMutableTreeNode("纯净新闻"));
    newsType.add(new DefaultMutableTreeNode("特稿与特写"));
    newsType.add(new DefaultMutableTreeNode("评论"));
    newsType.add(new DefaultMutableTreeNode("其他"));

    DefaultMutableTreeNode helpType = new DefaultMutableTreeNode("社会帮助与关爱");
    helpType.add(new DefaultMutableTreeNode("单纯一次捐款捐物"));
    helpType.add(new DefaultMutableTreeNode("旅游活动安排的项目之一"));
    helpType.add(new DefaultMutableTreeNode("免费开放"));
    helpType.add(new DefaultMutableTreeNode("设立长期资助项目"));
    helpType.add(new DefaultMutableTreeNode("其他"));

    DefaultMutableTreeNode newsTheme = new DefaultMutableTreeNode("报道主题");
    newsTheme.add(helpType);
    newsTheme.add(new DefaultMutableTreeNode("社会建议与看法"));
    newsTheme.add(new DefaultMutableTreeNode("表彰单位或个人"));
    newsTheme.add(new DefaultMutableTreeNode("留守儿童遭受暴力殴打"));
    newsTheme.add(new DefaultMutableTreeNode("留守儿童遭受性侵等"));
    newsTheme.add(new DefaultMutableTreeNode("留守儿童意外死忙"));
    newsTheme.add(new DefaultMutableTreeNode("留守儿童努力上进"));
    newsTheme.add(new DefaultMutableTreeNode("父母在城市的艰难生活"));
    newsTheme.add(new DefaultMutableTreeNode("其他"));

    DefaultMutableTreeNode helpSponsor = new DefaultMutableTreeNode("帮助发起人");
    helpSponsor.add(new DefaultMutableTreeNode("政府部门"));
    helpSponsor.add(new DefaultMutableTreeNode("企业"));
    helpSponsor.add(new DefaultMutableTreeNode("事业单位"));
    helpSponsor.add(new DefaultMutableTreeNode("公益团体"));
    helpSponsor.add(new DefaultMutableTreeNode("个人"));

    DefaultMutableTreeNode awardBody = new DefaultMutableTreeNode("奖励对象");
    awardBody.add(new DefaultMutableTreeNode("政府部门"));
    awardBody.add(new DefaultMutableTreeNode("企业"));
    awardBody.add(new DefaultMutableTreeNode("事业单位"));
    awardBody.add(new DefaultMutableTreeNode("公益团体"));
    awardBody.add(new DefaultMutableTreeNode("个人"));

    DefaultMutableTreeNode newsImage = new DefaultMutableTreeNode("报道形象");
    newsImage.add(new DefaultMutableTreeNode("积极健康"));
    newsImage.add(new DefaultMutableTreeNode("可怜悲惨"));
    newsImage.add(new DefaultMutableTreeNode("沐恩幸福"));
    newsImage.add(new DefaultMutableTreeNode("问题儿童"));
    newsImage.add(new DefaultMutableTreeNode("其他"));

    DefaultMutableTreeNode allLabel = new DefaultMutableTreeNode("可选标签");
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
    setTitle("留守儿童新闻报导");

    setLayout(null);
  }

}
