package teamwork.window;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import teamwork.listener.ListDoubleClickListener;
import teamwork.listener.LoadFileListener;
import teamwork.listener.RecycleBinListener;
import teamwork.model.News;
import teamwork.model.NewsListModel;
import teamwork.r.R;

public class MainWindow extends AbstractWindow {

  private static final long serialVersionUID = 1L;

  private JButton loadFileButton;
  private JButton updateButton;
  private JButton statisticsButton;
  private JButton deleteButton;
  private JButton saveButton;
  private JButton loadButton;
  private JButton recycleButton;

  private JList<News> newsList;

  private JLabel numLabel;

  private JTree parentTree;

  @Override
  protected void addListener() {
    newsList.addMouseListener(new ListDoubleClickListener());
    loadFileButton.addActionListener(new LoadFileListener());
    recycleButton.addActionListener(new RecycleBinListener());
  }

  @Override
  protected void regitstComponent() {
    R r = R.getInstance();
    r.registObject("newsList", newsList);
    r.registObject("numLabel", numLabel);
  }

  @Override
  protected void init() {
    Font font = new Font("宋体", 1, 20);

    loadFileButton = new JButton("读取文件");
    loadFileButton.setFont(font);
    updateButton = new JButton("加载内容");
    updateButton.setFont(font);
    statisticsButton = new JButton("统计结果");
    statisticsButton.setFont(font);
    deleteButton = new JButton("删除新闻");
    deleteButton.setFont(font);
    saveButton = new JButton("保存进度");
    saveButton.setFont(font);
    loadButton = new JButton("读取进度");
    loadButton.setFont(font);
    recycleButton = new JButton("回收站");
    recycleButton.setFont(font);

    loadFileButton.setBounds(10, 30, 120, 40);
    updateButton.setBounds(10, 108, 120, 40);
    statisticsButton.setBounds(10, 186, 120, 40);
    deleteButton.setBounds(10, 264, 120, 40);
    saveButton.setBounds(10, 342, 120, 40);
    loadButton.setBounds(10, 420, 120, 40);
    recycleButton.setBounds(10, 498, 120, 40);

    add(loadFileButton);
    add(updateButton);
    add(statisticsButton);
    add(deleteButton);
    add(saveButton);
    add(loadButton);
    add(recycleButton);

    JLabel tagLabel = new JLabel("标签：");
    tagLabel.setFont(font);

    newsList = new JList<News>(new NewsListModel());
    JScrollPane scrollPane = new JScrollPane(newsList);

    numLabel = new JLabel("一共有 XXX 条新闻");
    numLabel.setFont(font);

    add(tagLabel);
    add(scrollPane);
    add(numLabel);

    tagLabel.setBounds(170, 30, 70, 30);
    newsList.setSize(10, 10);
    scrollPane.setBounds(170, 70, 600, 430);
    numLabel.setBounds(310, 510, 270, 30);

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
    add(treePane);
    treePane.setBounds(780, 30, 200, 510);

    this.validate();

  }

  @Override
  protected void initWindow() {
    super.initWindow();
    
    setTitle("留守儿童舆情调查软件");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 关闭的默认操作：退出程序

    setLayout(null);// 将界面设置为空布局
  }
}
