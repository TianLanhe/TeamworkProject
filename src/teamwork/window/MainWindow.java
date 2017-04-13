package teamwork.window;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import teamwork.listener.ListDoubleClickListener;

public class MainWindow extends AbstractWindow {

  private static final long serialVersionUID = 1L;

  private JPanel controlPanel;
  private JPanel tagPanel;

  private JButton updateButton;
  private JButton statisticsButton;
  private JButton deleteButton;
  private JButton saveButton;
  private JButton loadButton;
  private JButton recycleButton;

  private JButton centerButton;
  private JButton provinceButton;
  private JButton cityButton;

  private JList<String> newsList;

  private JLabel numLabel;
  private JLabel tagLabel;

  @Override
  protected void addListener() {
    newsList.addMouseListener(new ListDoubleClickListener());
  }

  @Override
  protected void regitstComponent() {

  }

  @Override
  protected void init() {
    controlPanel = new JPanel();
    controlPanel.setLayout(null);

    Font font = new Font("宋体", 1, 20);

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

    controlPanel.add(updateButton);
    controlPanel.add(statisticsButton);
    controlPanel.add(deleteButton);
    controlPanel.add(saveButton);
    controlPanel.add(loadButton);
    controlPanel.add(recycleButton);

    updateButton.setBounds(10, 30, 120, 40);
    statisticsButton.setBounds(10, 122, 120, 40);
    deleteButton.setBounds(10, 214, 120, 40);
    saveButton.setBounds(10, 306, 120, 40);
    loadButton.setBounds(10, 398, 120, 40);
    recycleButton.setBounds(10, 490, 120, 40);

    add(controlPanel);
    controlPanel.setBounds(0, 0, 140, 600);


    tagLabel = new JLabel("标签：");
    tagLabel.setFont(font);
    String[] title =
        {"李长春在听取青少年思想道德建设督查情况汇报时强调采取积极有效措施狠抓各项工作落实...", "第27届北京青少年科技创新大赛闭幕30余万青少年参与社科、环保是研究热点",
            "点亮家乡学生心中的明灯——记张家口市第一私立中学心理辅导老师刘树华", "600万农民工进城子女2200万“留守儿童”义务教育成难题人大常委会执法检查组建...",
            "人大常委会第二十八次会议举行第三次全体会议吴邦国出席听取义务教育法执法检查报告、...", "教育均衡：关爱农村留守儿童——湖南卫视《变形记》引出的教育话题",
            "第27届北京青少年科技创新大赛闭幕30余万青少年参与社科、环保是研究热点", "“我们也是中国的娃，祖国的花”——对江苏“留守儿童”生活现状的调查",
            "培育和践行社会主义核心价值观·百家经验 湖北宜昌：小网格迸发大能量", "以人民为中心 与时代相结合 紮根人民 紮根生活 ——“深入生活、紮根人民”主题实...",
            "行进中国·精彩故事 把山里娃带上“希望之船”——再访首届“最美乡村教师”获得者石...",
            "八年来，“白求恩医疗队”转战太行，开展义诊巡诊、技术帮带和健康宣教，老区群衆说—...", "他心里装着百姓——记上海奚家港边防派出所团结沙警务区警长朱一帆",
            "服务定制 温暖到心——各地团组织、青联学联组织为困难青少年送温暖", "主流文艺作品要引人向善——电影《爱·回家》全国公益巡映啓动仪式暨文艺作品与社会责...",
            "让“大雁”们早日融入广州 ——广州实施“金雁计划”关爱外来工纪实", "书香润乡村 “微光”获点赞 ——记河南内黄农家女李翠利和她的微光书苑",
            "思想先导 价值引领 ——党的十八大以来精神文明建设工作综述之一", "中国人民政治协商会议第十二届全国委员会提案委员会关於政协十二届三次会议提案审查情...",
            "政府工作报告——二○一五年三月五日在第十二届全国人民代表大会第三次会议上", "最高人民检察院工作报告 ——二〇一五年三月十二日在第十二届全国人民代表大会第三次...",
            "友善像花儿一样——湖南商学院大学生肖霞照顾邻居、远征军遗孀的故事", "习近平在贵州调研时强调 看清形势适应趋势发挥优势 善於运用辩证思维谋划发展",
            "让精神卫生驶上“快车道”——《全国精神卫生工作规划（2015-2020年）》解读", "“我不知道明天和意外哪一个先来”——记辽宁锦州北镇市正二村党支部书记王桂兰（上）",
            "留守儿童问题将长期存在，这需要我们调整思维，寻找缓解留守儿童问题的有效路径——难..."};
    newsList = new JList<String>(title);
    numLabel = new JLabel("一共有 XXX 条新闻");
    numLabel.setFont(font);

    JScrollPane scrollPane = new JScrollPane(newsList);
    add(tagLabel);
    add(scrollPane);
    add(numLabel);

    tagLabel.setBounds(170, 30, 70, 30);
    newsList.setSize(10, 10);
    scrollPane.setBounds(170, 70, 600, 430);
    numLabel.setBounds(310, 510, 270, 30);

    tagPanel = new JPanel();
    tagPanel.setBorder(BorderFactory.createEtchedBorder());

    JLabel paperCatalog = new JLabel("报纸类别：");

    centerButton = new JButton("中央党报");
    provinceButton = new JButton("省级党报");
    cityButton = new JButton("都市党报");

    tagPanel.add(paperCatalog);
    tagPanel.add(centerButton);
    tagPanel.add(provinceButton);
    tagPanel.add(cityButton);

    paperCatalog.setBounds(10, 10, 80, 20);
    centerButton.setBounds(10, 30, 90, 20);
    provinceButton.setBounds(10, 55, 90, 20);
    cityButton.setBounds(10, 80, 90, 20);

    add(tagPanel);
    tagPanel.setBounds(780, 30, 200, 510);
  }

  @Override
  protected void initWindow() {
    super.initWindow();

    setTitle("留守儿童舆情调查软件");

    setSize(1000, 600);
    setLocationRelativeTo(null);

    setLayout(null);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 关闭的默认操作：退出程序
  }
}
