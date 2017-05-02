package teamwork.window;

import javax.swing.JFrame;

public abstract class AbstractWindow extends JFrame {
  private static final long serialVersionUID = 1L;
  
  public final void run(){
    initWindow(); //�Դ��ڵĳ�ʼ������
    init(); // ��ʼ������
    regitstComponent(); // ע�����
    addListener(); // ��Ӽ�����
  }

  protected abstract void addListener();

  protected abstract void regitstComponent();

  protected abstract void init();
  
  protected void initWindow(){
    setSize(1000, 600);
    setLocationRelativeTo(null); // ���þ�����ʾ
    setVisible(true);// Ĭ�Ͽɼ�
    setResizable(false);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// �رյ�Ĭ�ϲ������رմ���
  }
}

