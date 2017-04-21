package teamwork.window;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import teamwork.listener.ListDoubleClickListener;

public class MainWindow extends AbstractWindow {

  private static final long serialVersionUID = 1L;

  private JPanel controlPanel;
  private JPanel tagPanel;

  private JButton loadFileButton;
  private JButton updateButton;
  private JButton statisticsButton;
  private JButton deleteButton;
  private JButton saveButton;
  private JButton loadButton;
  private JButton recycleButton;

  private JList<String> newsList;

  private JLabel numLabel;
  private JLabel tagLabel;
  
  private JTree parentTree;

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

    Font font = new Font("����", 1, 20);

    loadFileButton = new JButton("��ȡ�ļ�");
    loadFileButton.setFont(font);
    updateButton = new JButton("��������");
    updateButton.setFont(font);
    statisticsButton = new JButton("ͳ�ƽ��");
    statisticsButton.setFont(font);
    deleteButton = new JButton("ɾ������");
    deleteButton.setFont(font);
    saveButton = new JButton("�������");
    saveButton.setFont(font);
    loadButton = new JButton("��ȡ����");
    loadButton.setFont(font);
    recycleButton = new JButton("����վ");
    recycleButton.setFont(font);

    controlPanel.add(loadFileButton);
    controlPanel.add(updateButton);
    controlPanel.add(statisticsButton);
    controlPanel.add(deleteButton);
    controlPanel.add(saveButton);
    controlPanel.add(loadButton);
    controlPanel.add(recycleButton);

    loadFileButton.setBounds(10, 30, 120, 40);
    updateButton.setBounds(10, 108, 120, 40);
    statisticsButton.setBounds(10, 186, 120, 40);
    deleteButton.setBounds(10, 264, 120, 40);
    saveButton.setBounds(10, 342, 120, 40);
    loadButton.setBounds(10, 420, 120, 40);
    recycleButton.setBounds(10, 498, 120, 40);

    add(controlPanel);
    controlPanel.setBounds(0, 0, 140, 600);


    tagLabel = new JLabel("��ǩ��");
    tagLabel.setFont(font);
    String[] title =
        {"�������ȡ������˼����½��趽������㱨ʱǿ����ȡ������Ч��ʩ��ץ�������ʵ...", "��27�챱��������Ƽ����´�����Ļ30���������������ơ��������о��ȵ�",
            "��������ѧ�����е����ơ������żҿ��е�һ˽����ѧ��������ʦ������", "600��ũ�񹤽�����Ů2200�����ض�ͯ����������������˴�ί��ִ������齨...",
            "�˴�ί��ڶ�ʮ�˴λ�����е�����ȫ�����������ϯ��ȡ���������ִ����鱨�桢...", "�������⣺�ذ�ũ�����ض�ͯ�����������ӡ����μǡ������Ľ�������",
            "��27�챱��������Ƽ����´�����Ļ30���������������ơ��������о��ȵ�", "������Ҳ���й����ޣ�����Ļ��������Խ��ա����ض�ͯ��������״�ĵ���",
            "�����ͼ������������ļ�ֵ�ۡ��ټҾ��� �����˲���С����ŷ�������", "������Ϊ���� ��ʱ������ �������� �������� �������������������������ʵ...",
            "�н��й������ʹ��� ��ɽ���޴��ϡ�ϣ��֮���������ٷ��׽조��������ʦ�������ʯ...",
            "���������������ҽ�ƶӡ�תս̫�У���չ����Ѳ���������ͽ������̣�����Ⱥ�\˵��...", "������װ�Ű��ա������Ϻ��ɼҸ۱߷��ɳ����Ž�ɳ������������һ��",
            "������ ��ů���ġ�����������֯������ѧ����֯Ϊ��������������ů", "����������ƷҪ�������ơ�����Ӱ�������ؼҡ�ȫ������Ѳӳ������ʽ��������Ʒ�������...",
            "�á����㡱������������� ��������ʵʩ������ƻ����ذ���������ʵ", "��������� ��΢�⡱����� �����Ǻ����ڻ�ũ��Ů�����������΢����Է",
            "˼���ȵ� ��ֵ���� ��������ʮ�˴����������������蹤������֮һ", "�й���������Э�̻����ʮ����ȫ��ίԱ���᰸ίԱ������Эʮ�������λ����᰸�����...",
            "�����������桪������һ�������������ڵ�ʮ����ȫ���������������λ�����", "���������Ժ�������� ��������һ��������ʮ�����ڵ�ʮ����ȫ����������������...",
            "�����񻨶�һ������������ѧԺ��ѧ��Фϼ�չ��ھӡ�Զ�������׵Ĺ���", "ϰ��ƽ�ڹ��ݵ���ʱǿ�� ����������Ӧ���Ʒ������� ������ñ�֤˼άı����չ",
            "�þ�������ʻ�ϡ��쳵����������ȫ���������������滮��2015-2020�꣩�����", "���Ҳ�֪�������������һ���������������������ݱ����������嵳֧��������������ϣ�",
            "���ض�ͯ���⽫���ڴ��ڣ�����Ҫ���ǵ���˼ά��Ѱ�һ������ض�ͯ�������Ч·��������..."};
    newsList = new JList<String>(title);
    numLabel = new JLabel("һ���� XXX ������");
    numLabel.setFont(font);

    JScrollPane scrollPane = new JScrollPane(newsList);
    add(tagLabel);
    add(scrollPane);
    add(numLabel);

    tagLabel.setBounds(170, 30, 70, 30);
    newsList.setSize(10, 10);
    scrollPane.setBounds(170, 70, 600, 430);
    numLabel.setBounds(310, 510, 270, 30);
    
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

    
    tagPanel = new JPanel();
    tagPanel.setBorder(BorderFactory.createEtchedBorder());

    
    tagPanel.setBounds(780, 30, 200, 510);
    
    parentTree = new JTree(allLabel);
    JScrollPane treePane = new JScrollPane(parentTree);
    add(treePane);
    treePane.setBounds(780, 30, 200, 510);
    
    this.validate();
    
  }

  @Override
  protected void initWindow() {
    super.initWindow();

    setTitle("���ض�ͯ����������");

    setSize(1000, 600);
    setLocationRelativeTo(null);

    setLayout(null);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// �رյ�Ĭ�ϲ������˳�����
  }
}
