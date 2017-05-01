package teamwork.window;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class RecycleBin extends AbstractWindow {

  private static final long serialVersionUID = 1L;

  private JPanel down;
  private JButton huanyuan;
  private JList<String> content;
  private JButton qingkong;
  private JButton chedi;


  @Override
  protected void addListener() {}

  @Override
  protected void regitstComponent() {}

  @Override
  protected void init() {
    huanyuan = new JButton("还原");
    qingkong = new JButton("清空");
    chedi = new JButton("彻底删除");
    down = new JPanel();

    // 在布局上添加控件
    down = new JPanel();
    down.setLayout(null);// 设为空布局
    down.add(huanyuan);
    huanyuan.setBounds(150, 500, 100, 40);
    down.add(qingkong);
    qingkong.setBounds(350, 500, 100, 40);
    down.add(chedi);
    chedi.setBounds(550, 500, 120, 40);

    String[] title = {"新闻标题"};

    content = new JList<String>(title);
    down.add(content);
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
