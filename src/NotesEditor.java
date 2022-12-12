import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class NotesEditor implements ActionListener {
    //frame for text editor
    JFrame frame;
    //menu bar for containing menus
    JMenuBar menuBar;
    //Menus
    JMenu file, edit;

    //Menu items for file menu
    JMenuItem newFile, openFile, saveFile;

    //Menu Items for edit menu
    JMenuItem cut, copy, paste, selectALL, close;

    //Area for writing notes
    JTextArea textArea;



    //constructor
    NotesEditor(){
        //Initialize frame
        frame = new JFrame();

        //Initialize text area
        textArea = new JTextArea();

        //Initialize menuBar
        menuBar = new JMenuBar();

        //Initialize menus
        file = new JMenu("File");
        edit = new JMenu("Edit");

        //Initialize menu items for the file menu
        newFile = new JMenuItem("New");
        openFile = new JMenuItem("Open");
        saveFile = new JMenuItem("Save");

        //Add Action listeners to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);


        //Adding menu items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //Initialize menu items for edit menu
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectALL = new JMenuItem("Select All");
        close = new JMenuItem("Close Window");

        //Add Action listeners to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectALL.addActionListener(this);
        close.addActionListener(this);

        //Adding menu items to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectALL);
        edit.add(close);


        //add menus to menuBar
        menuBar.add(file);
        menuBar.add(edit);


        //Add menuBar to frame
        frame.setJMenuBar(menuBar);

        //Adding text area to frame
        frame.add(textArea);


        //set dimensions of frame
        frame.setBounds(100, 100, 800, 500);
        //Mark frame unhidden
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        //if source is cut
        if(actionEvent.getSource() == cut){
           //perform action according to cut event
            textArea.cut();
        }

        //if source is copy
        if(actionEvent.getSource() == copy){
            //perform action according to copy event
            textArea.copy();
        }

        //if source is paste
        if(actionEvent.getSource() == paste){
            //perform paste
            textArea.paste();
        }

        //if source is selectAll
        if(actionEvent.getSource() == selectALL){
            //perform selectAll
            textArea.selectAll();
        }

        //if source is close
        if(actionEvent.getSource() == close){
            //perform close
            System.exit(0);
        }

        //if source is new
        if(actionEvent.getSource() == newFile){
            //create a new window
            NotesEditor newWindow = new NotesEditor();
        }

        //if source is open
        if(actionEvent.getSource() == openFile){
            //open a text file
            //Initialize file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            //get choose option for file chooser
            int chooseOption = fileChooser.showOpenDialog(null);
            //if choose option is equal to approve
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                //get selected file
                File file = fileChooser.getSelectedFile();
                //get selected file path
                String filePath = file.getPath();
                try{
                    //create buffered reader
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
                    //create strings to store content of file
                    String intermediate = "", output = "";
                    //read file line by line
                    while((intermediate = bufferedReader.readLine()) != null){
                        output += intermediate + "\n";
                    }
                    //set output to the text area
                    textArea.setText(output);

                }catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        }

        //if source is save
        if(actionEvent.getSource() == saveFile){
            //save a file
            // create a file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            //get chosen option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            //if choose option is approve
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                //create a file object with selected path
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try {
                    //create buffered writer to write content to file
                    BufferedWriter outFile = new BufferedWriter(new FileWriter(file));
                    //get content to text area from outfile
                    textArea.write(outFile);
                    outFile.close();
                }catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        //initialize NotesEditor
        NotesEditor NotesEditor = new NotesEditor();
    }
}