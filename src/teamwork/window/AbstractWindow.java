package teamwork.window;

import javax.swing.JFrame;

public abstract class AbstractWindow extends JFrame {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /*public AbstractWindow() {
    super();

    init(); // 初始化界面
    regitstComponent(); // 注册组件
    addListener(); // 添加监听器

    
  }*/

  protected abstract void addListener();

  protected abstract void regitstComponent();

  protected abstract void init();
}