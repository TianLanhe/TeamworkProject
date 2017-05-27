package teamwork.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


public class SaveListener implements ActionListener {

  private BufferedWriter out;
  private FileWriter fileWriter;

  @Override
  public void actionPerformed(ActionEvent e) {
    JFileChooser jfc = new JFileChooser();
    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
    jfc.setSelectedFile(new File(".mmp"));
    FileNameExtensionFilter filter = new FileNameExtensionFilter(  
      "�����ļ�(*.mmp)", "mmp");  
    jfc.setFileFilter(filter); 
    jfc.setCurrentDirectory(jfc.getSelectedFile());
    //jfc.addChoosableFileFilter(new MmpFileFilter("�����ļ�(*.mmp)"));
    
    int state = jfc.showSaveDialog(null);
    if (state == JFileChooser.APPROVE_OPTION) {
      try {
        File dir = jfc.getCurrentDirectory();
        String name = jfc.getSelectedFile().getName();
        File file = new File(dir, name);
       
        if(file.exists()){//�Ѵ����ļ�
          JOptionPane.showConfirmDialog(null, file + "�ļ��Ѿ�����,�Ƿ񸲸�?", "�ļ�����", JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);//��ʾһ���Ի�����ʵ���Ƿ񸲸�Դ�ļ�
        }
        
        if(name.indexOf(".mmp")==-1){
          file = new File(jfc.getCurrentDirectory(),name+".mmp");
        }
       
        fileWriter = new FileWriter(file);
        out = new BufferedWriter(fileWriter);
        out.close();
        fileWriter.close();
      } catch (IOException exp) {
        System.err.println("�����쳣");  
        exp.printStackTrace();
      }
    
    }
  }

}
