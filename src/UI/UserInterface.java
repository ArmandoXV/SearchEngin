package UI;

import first.app.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFrame;

public class UserInterface {
    private JPanel panel_Main;
    private JPanel Panel_Top;
    private JPanel Panel_Down;
    private JLabel lbl_Status;
    private JButton dtn_IndexDir;
    private JTextField txt_IndexDir;
    private JButton btn_Search;
    private JTextField txt_SearchQeury;
    private JButton btn_DataDir;
    private JTextField txt_DataDir;
    private JButton btn_RSSFeedDir;
    private JTextField txt_RssFeedDir;
    private JTextArea txt_res;


    public String dataDir;
    public String indexDir;
    public String rssFeedDir;

    public static void main(String[] args) {
        JFrame frame = new JFrame("UserInterface");
        frame.setContentPane(new UserInterface().panel_Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main() {
        UserInterface nw = new UserInterface();
    }

    public UserInterface() {

        dtn_IndexDir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int option = fileChooser.showOpenDialog(new JFrame());
                if(option == JFileChooser.APPROVE_OPTION){
                    File file = fileChooser.getSelectedFile();
                    indexDir = file.getAbsolutePath();
                    txt_IndexDir.setText(file.getAbsolutePath());
                    Controller.setIndexDir(indexDir);
                    lbl_Status.setText("Folder Selected: " + file.getAbsolutePath());
                }else{
                    lbl_Status.setText("Open command canceled");
                }

            }
        });
        btn_DataDir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int option = fileChooser.showOpenDialog(new JFrame());
                if(option == JFileChooser.APPROVE_OPTION){
                    File file = fileChooser.getSelectedFile();
                    dataDir = file.getAbsolutePath();
                    txt_DataDir.setText(file.getAbsolutePath());
                    Controller.setDataDir(dataDir);
                    lbl_Status.setText("Folder Selected: " + file.getAbsolutePath());
                }else{
                    lbl_Status.setText("Open command canceled");
                }
            }
        });
        btn_RSSFeedDir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                //fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int option = fileChooser.showOpenDialog(new JFrame());
                if(option == JFileChooser.APPROVE_OPTION){
                    File file = fileChooser.getSelectedFile();
                    rssFeedDir = file.getAbsolutePath();
                    txt_RssFeedDir.setText(file.getAbsolutePath());
                    Controller.setUri(rssFeedDir);
                    Controller.ReadAndWriteData();
                    lbl_Status.setText("Folder Selected: " + file.getAbsolutePath());
                }else{
                    lbl_Status.setText("Open command canceled");
                }
            }
        });
        btn_Search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txt_res.setText(Controller.Search(txt_SearchQeury.getText()));
            }
        });
    }


}
