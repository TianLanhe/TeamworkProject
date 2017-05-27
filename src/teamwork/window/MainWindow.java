package teamwork.window;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;

import teamwork.listener.DeleteNewsListener;
import teamwork.listener.ListDoubleClickListener;
import teamwork.listener.LoadFileListener;
import teamwork.listener.LoadListener;
import teamwork.listener.NewsTreeSelectionListener;
import teamwork.listener.OpenRecycleBinListener;
import teamwork.listener.SaveListener;
import teamwork.listener.UpdateContentListener;
import teamwork.model.ClassCatalog;
import teamwork.model.News;
import teamwork.model.NewsListModel;
import teamwork.model.NewsTreeModel;
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

  private JLabel numLabel;// ��ʾ��������
  private JLabel tagLabel;// ��ʾ��ǩ����

  private JTree parentTree;// ������ǩ�б�

  @Override
  protected void addListener() {
    newsList.addMouseListener(new ListDoubleClickListener());

    loadFileButton.addActionListener(new LoadFileListener());
    updateButton.addActionListener(new UpdateContentListener());
    recycleButton.addActionListener(new OpenRecycleBinListener());
    saveButton.addActionListener(new SaveListener());
    loadButton.addActionListener(new LoadListener());
    deleteButton.addActionListener(new DeleteNewsListener());

    parentTree.addTreeSelectionListener(new NewsTreeSelectionListener());
  }

  @Override
  protected void regitstComponent() {
    R r = R.getInstance();
    r.registObject("numLabel", numLabel);
    r.registObject("newsList", newsList);
    r.registObject("tagsTree", parentTree);
    r.registObject("tagLabel", tagLabel);
  }

  @Override
  protected void init() {
    Font font = new Font("����", 1, 20);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(null);

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

    loadFileButton.setBounds(10, 30, 120, 40);
    updateButton.setBounds(10, 108, 120, 40);
    statisticsButton.setBounds(10, 186, 120, 40);
    deleteButton.setBounds(10, 264, 120, 40);
    saveButton.setBounds(10, 342, 120, 40);
    loadButton.setBounds(10, 420, 120, 40);
    recycleButton.setBounds(10, 498, 120, 40);

    buttonPanel.add(loadFileButton);
    buttonPanel.add(updateButton);
    buttonPanel.add(statisticsButton);
    buttonPanel.add(deleteButton);
    buttonPanel.add(saveButton);
    buttonPanel.add(loadButton);
    buttonPanel.add(recycleButton);

    buttonPanel.setBounds(10, 0, 130, 600);
    add(buttonPanel);

    JLabel label = new JLabel("��ǩ��");
    label.setFont(font);

    tagLabel = new JLabel("");
    tagLabel.setFont(font);
    tagLabel.setForeground(Color.blue);

    newsList = new JList<News>(new NewsListModel<News>());
    JScrollPane scrollPane = new JScrollPane(newsList);
    newsList.setFont(new Font("����", 1, 14));// ��������
    newsList.setFixedCellHeight(24);// �������

    numLabel = new JLabel("һ���� XXX ������");
    numLabel.setFont(font);

    add(label);
    add(tagLabel);
    add(scrollPane);
    add(numLabel);

    label.setBounds(170, 30, 70, 30);
    tagLabel.setBounds(250, 30, 400, 30);
    // newsList.setSize(10, 10);
    scrollPane.setBounds(155, 70, 615, 430);
    numLabel.setBounds(310, 510, 270, 30);

    NewsTreeModel treeModel = new NewsTreeModel(ClassCatalog.getInstance());
    parentTree = new JTree(treeModel);
    parentTree.setRowHeight(20);// �������
    parentTree.setRootVisible(false);// ���ڵ㲻�ɼ�
    parentTree.setToggleClickCount(1);// ����һ��չ���ڵ�
    parentTree.setFont(new Font("����", 0, 14));

    JScrollPane treePane = new JScrollPane(parentTree);

    add(treePane);
    treePane.setBounds(780, 30, 200, 510);

    this.validate();
  }

  @Override
  protected void initWindow() {
    super.initWindow();

    setTitle("���ض�ͯ����������");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// �رյ�Ĭ�ϲ������˳�����

    setLayout(null);// ����������Ϊ�ղ���
  }
}
