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

    title = new JTextField("新闻标题");
    source = new JTextField("来源网站");
    author = new JTextField("作者");
    date = new JTextField("日期");
    type = new JTextField("类别");
    content = new JTextArea("新闻内容", 20, 1000);
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
    title.setFont(new Font("黑体", Font.BOLD, 20));
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

    JLabel tagLabel = new JLabel("已选标签：");
    tagLabel.setBounds(800, 0, 70, 20);
    labelPane.add(tagLabel);

    JTextArea label = new JTextArea();
    labelPane.add(label);
    label.setBounds(800, 20, 180, 180);

    JButton addLabel = new JButton("添加");
    labelPane.add(addLabel);
    addLabel.setBounds(820, 210, 60, 30);

    JButton deleteLabel = new JButton("删除");
    labelPane.add(deleteLabel);
    deleteLabel.setBounds(900, 210, 60, 30);

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
    labelPane.add(treePane);
    treePane.setBounds(800, 250, 200, 300);
    
    tagLabel.repaint();
    label.repaint();
    this.validate();
    
  }

  @Override
  protected void initWindow() {
    super.initWindow();
    setTitle("留守儿童新闻报导");
    setSize(1000, 600);
    setLocationRelativeTo(null); // 设置居中显示
  }

}
