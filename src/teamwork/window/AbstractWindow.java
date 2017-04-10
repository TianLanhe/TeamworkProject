package teamwork.window;

import javax.swing.JFrame;

public abstract class AbstractWindow extends JFrame {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /*public AbstractWindow() {
    super();

    init(); // ��ʼ������
    regitstComponent(); // ע�����
    addListener(); // ��Ӽ�����

    
  }*/

  protected abstract void addListener();

  protected abstract void regitstComponent();

  protected abstract void init();
}