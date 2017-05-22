package teamwork.window;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import teamwork.listener.DeleteAllListener;
import teamwork.listener.PermanentlyDeleteListener;
import teamwork.listener.RevokeDeleteListener;
import teamwork.model.BinCatalog;
import teamwork.model.News;
import teamwork.model.NewsListModel;
import teamwork.r.R;


public class RecycleBinWindow extends AbstractWindow {

  private static final long serialVersionUID = 1L;

  private JPanel down;
  private JButton revoke;// 撤销删除
  private JList<News> content;// 被删除的新闻列表
  private JButton clear;// 清空回收站
  private JButton chedi;// 彻底删除新闻

  @Override
  protected void addListener() {
    revoke.addActionListener(new RevokeDeleteListener());
    clear.addActionListener(new DeleteAllListener());
    chedi.addActionListener(new PermanentlyDeleteListener());
  }

  @Override
  protected void regitstComponent() {
    R.getInstance().registObject("newsList", content);
  }

  @Override
  protected void init() {
    Font font = new Font("宋体", 1, 20);

    revoke = new JButton("还原");
    revoke.setFont(font);
    clear = new JButton("清空");
    clear.setFont(font);
    chedi = new JButton("彻底删除");
    chedi.setFont(font);

    down = new JPanel();
    down.setLayout(null);// 设为空布局

    down.add(revoke);
    revoke.setBounds(150, 500, 100, 40);
    down.add(clear);
    clear.setBounds(350, 500, 100, 40);
    down.add(chedi);
    chedi.setBounds(550, 500, 120, 40);

    NewsListModel<News> model = new NewsListModel<News>(BinCatalog.getInstance().getNewsList());
    content = new JList<News>(model);
    content.setFont(new Font("宋体", 1, 14));// 调整字体
    content.setFixedCellHeight(24);// 调整间距

    JScrollPane scrollPane = new JScrollPane(content);
    down.add(scrollPane);
    scrollPane.setBounds(40, 30, 720, 450);

    // 添加布局
    add(down);
    down.setBounds(0, 0, 800, 600);
  }

  @Override
  protected void initWindow() {
    super.initWindow();

    setTitle("回收站");
    setSize(800, 600);
    setLocationRelativeTo(null);
  }

}
