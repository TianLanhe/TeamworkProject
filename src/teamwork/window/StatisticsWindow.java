package teamwork.window;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;

import teamwork.listener.NewsTreeStatisticsListener;
import teamwork.model.ClassCatalog;
import teamwork.model.viewmodel.NewsTreeModel;
import teamwork.r.R;

public class StatisticsWindow extends AbstractWindow {

  private static final long serialVersionUID = 1L;

  private JLabel numLabel;// ��ʾ��������
  private JLabel tagLabel;// ��ʾ��ǩ����

  private JTree parentTree;// ��ǩѡ����
  private JPanel diagramPanel;// ͼ���

  @Override
  protected void addListener() {
    parentTree.addTreeSelectionListener(new NewsTreeStatisticsListener());
  }

  @Override
  protected void regitstComponent() {
    R r = R.getInstance();
    r.registObject("tagsTree", parentTree);
    r.registObject("diagramPanel", diagramPanel);
    r.registObject("other_numLabel", numLabel);
    r.registObject("other_tagLabel", tagLabel);
  }

  @Override
  protected void init() {
    Font font = new Font("����", 1, 20);

    NewsTreeModel treeModel = new NewsTreeModel(ClassCatalog.getInstance());
    parentTree = new JTree(treeModel);
    parentTree.setRowHeight(20);// �������
    parentTree.setRootVisible(false);// ���ڵ㲻�ɼ�
    parentTree.setToggleClickCount(2);// ��������չ���ڵ�
    parentTree.setFont(new Font("����", 0, 14));

    JScrollPane treePane = new JScrollPane(parentTree);

    add(treePane);
    treePane.setBounds(780, 30, 200, 516);

    JLabel label = new JLabel("��ǩ��");
    label.setFont(font);
    tagLabel = new JLabel("");
    tagLabel.setFont(font);
    tagLabel.setForeground(Color.blue);
    add(label);
    add(tagLabel);
    label.setBounds(70, 30, 70, 30);
    tagLabel.setBounds(150, 30, 400, 30);

    diagramPanel = new JPanel();
    add(diagramPanel);
    diagramPanel.setBounds(30, 70, 700, 440);
    diagramPanel.setBackground(Color.WHITE);

    numLabel = new JLabel("һ���� XXX ������");
    numLabel.setFont(font);
    add(numLabel);
    numLabel.setBounds(300, 510, 270, 30);

    this.validate();
  }

  @Override
  protected void initWindow() {
    super.initWindow();

    setTitle("ͳ�����ŷ�����");

    setLayout(null);// ����������Ϊ�ղ���
  }

}
