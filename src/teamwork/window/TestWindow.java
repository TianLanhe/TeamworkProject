package teamwork.window;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import teamwork.listener.DoTestListener;
import teamwork.listener.ListDoubleClickListener;
import teamwork.listener.LoadTestListener;
import teamwork.listener.SaveTestListener;
import teamwork.model.ClassCatalog;
import teamwork.model.News;
import teamwork.model.controler.TagsInitControler;
import teamwork.model.viewmodel.NewsListModel;
import teamwork.r.R;

public class TestWindow extends AbstractWindow {

  private static final long serialVersionUID = 1L;

  private JButton myTestButton;//读取测试按钮
  private JButton saveTestButton;// 保存测试按钮
  private JButton doTestButton;// 选取文件进行测试按钮

  private JList<News> newsList;
  
  private ClassCatalog catalog;

  private static boolean first = true;// 判断是不是第一次打开此界面，是的话初始化ClassCatalog

  public TestWindow() {
    catalog = ClassCatalog.getInstance("testCatalog");// 获得界面测试用的ClassCatalog
    if (first) {// 第一次打开则初始化ClassCatalog
      first = false;
      new TagsInitControler(catalog).init();
    }
  }

  @Override
  protected void addListener() {
    newsList.addMouseListener(new ListDoubleClickListener(catalog));

    myTestButton.addActionListener(new LoadTestListener());
    saveTestButton.addActionListener(new SaveTestListener());
    doTestButton.addActionListener(new DoTestListener());
  }

  @Override
  protected void regitstComponent() {
    R r = R.getInstance();
    r.registObject("testNewsList", newsList);
  }

  @Override
  protected void init() {
    Font font = new Font("宋体", 1, 20);

    JPanel testPanel = new JPanel();
    testPanel.setLayout(null);

    myTestButton = new JButton("我的测试");
    myTestButton.setFont(font);
    saveTestButton = new JButton("保存测试");
    saveTestButton.setFont(font);
    doTestButton = new JButton("进行测试");
    doTestButton.setFont(font);

    myTestButton.setBounds(160, 20, 120, 40);
    saveTestButton.setBounds(340, 20, 120, 40);
    doTestButton.setBounds(520, 20, 120, 40);

    testPanel.add(myTestButton);
    testPanel.add(saveTestButton);
    testPanel.add(doTestButton);

    testPanel.setBounds(0, 0, 800, 60);
    add(testPanel);

    JLabel label = new JLabel("测试新闻列表：");
    label.setFont(font);

    newsList = new JList<News>(new NewsListModel<News>());
    JScrollPane scrollPane = new JScrollPane(newsList);
    newsList.setFont(new Font("宋体", 1, 14));// 调整字体
    newsList.setFixedCellHeight(24);// 调整间距

    add(label);
    add(scrollPane);

    label.setBounds(80, 70, 160, 30);
    scrollPane.setBounds(80, 110, 640, 380);
  }

  @Override
  protected void initWindow() {
    super.initWindow();

    setTitle("合并测试");
    setSize(800, 540);
    setLayout(null);
    setLocationRelativeTo(null);
  }
}
