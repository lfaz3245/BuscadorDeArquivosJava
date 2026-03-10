package ui;

import model.FileResult;
import service.FileSearchService;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SearchUI extends JFrame {

    private JTextField directoryField;
    private JTextField searchField;

    private JTable table;
    private DefaultTableModel model;

    private JProgressBar progressBar;
    private JButton searchButton;

    private JPopupMenu popupMenu;

    public SearchUI() {

        setTitle("Buscador de Arquivos; Ass: Elifaz");
        setSize(950,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel topPanel = new JPanel(new GridLayout(2,1,5,5));

        JPanel dirPanel = new JPanel(new BorderLayout(5,5));

        directoryField = new JTextField();

        JButton chooseButton = new JButton("Selecionar Pasta");
        chooseButton.addActionListener(e -> chooseFolder());

        dirPanel.add(new JLabel("Diretório:"), BorderLayout.WEST);
        dirPanel.add(directoryField, BorderLayout.CENTER);
        dirPanel.add(chooseButton, BorderLayout.EAST);

        JPanel searchPanel = new JPanel(new BorderLayout(5,5));

        searchField = new JTextField();

        searchButton = new JButton("Buscar");
        searchButton.addActionListener(e -> startSearch());

        searchPanel.add(new JLabel("Buscar:"), BorderLayout.WEST);
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        topPanel.add(dirPanel);
        topPanel.add(searchPanel);

        add(topPanel, BorderLayout.NORTH);

        model = new DefaultTableModel(
                new String[]{"Nome","Tamanho","Modificado","Caminho"},0
        );

        table = new JTable(model);

        TableRowSorter<DefaultTableModel> sorter =
                new TableRowSorter<>(model);

        table.setRowSorter(sorter);

        table.getColumnModel().getColumn(0)
                .setCellRenderer(new FileIconRenderer());

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

        progressBar = new JProgressBar();
        add(progressBar, BorderLayout.SOUTH);

        createPopupMenu();

        table.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {

                if(e.getClickCount() == 2) {

                    openSelectedFile();
                }

                if(SwingUtilities.isRightMouseButton(e)) {

                    int row = table.rowAtPoint(e.getPoint());

                    table.setRowSelectionInterval(row,row);

                    popupMenu.show(table,e.getX(),e.getY());
                }
            }
        });
    }

    private void chooseFolder(){

        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){

            directoryField.setText(
                    chooser.getSelectedFile().getAbsolutePath()
            );
        }
    }

    private void startSearch(){

        model.setRowCount(0);

        String directory = directoryField.getText();
        String term = searchField.getText().toLowerCase();

        if(directory.isEmpty()){

            JOptionPane.showMessageDialog(this,"Selecione um diretório.");
            return;
        }

        Path path = Paths.get(directory);

        progressBar.setIndeterminate(true);
        searchButton.setEnabled(false);

        SwingWorker<Void, FileResult> worker = new SwingWorker<>() {

            protected Void doInBackground(){

                FileSearchService service = new FileSearchService();

                List<FileResult> results = service.search(path,term);

                for(FileResult r : results){

                    publish(r);
                }

                return null;
            }

            protected void process(List<FileResult> chunks){

                for(FileResult r : chunks){

                    model.addRow(new Object[]{
                            r.getName(),
                            r.getSize(),
                            r.getModified(),
                            r.getPath()
                    });
                }
            }

            protected void done(){

                progressBar.setIndeterminate(false);
                searchButton.setEnabled(true);
            }
        };

        worker.execute();
    }

    private void createPopupMenu(){

        popupMenu = new JPopupMenu();

        JMenuItem openFile = new JMenuItem("Abrir arquivo");
        openFile.addActionListener(e -> openSelectedFile());

        JMenuItem openFolder = new JMenuItem("Abrir local do arquivo");
        openFolder.addActionListener(e -> openFileLocation());

        JMenuItem copyPath = new JMenuItem("Copiar caminho");
        copyPath.addActionListener(e -> copyPath());

        popupMenu.add(openFile);
        popupMenu.add(openFolder);
        popupMenu.addSeparator();
        popupMenu.add(copyPath);
    }

    private String getSelectedPath(){

        int row = table.getSelectedRow();

        if(row < 0) return null;

        row = table.convertRowIndexToModel(row);

        return model.getValueAt(row,3).toString();
    }

    private void openSelectedFile(){

        try{

            String path = getSelectedPath();

            if(path != null){

                Desktop.getDesktop().open(new File(path));
            }

        }catch(Exception e){

            JOptionPane.showMessageDialog(this,"Não foi possível abrir o arquivo.");
        }
    }

    private void openFileLocation(){

        try{

            String path = getSelectedPath();

            if(path != null){

                File file = new File(path);

                Desktop.getDesktop().open(file.getParentFile());
            }

        }catch(Exception e){

            JOptionPane.showMessageDialog(this,"Não foi possível abrir o diretório.");
        }
    }

    private void copyPath(){

        String path = getSelectedPath();

        if(path != null){

            Toolkit.getDefaultToolkit()
                    .getSystemClipboard()
                    .setContents(
                            new java.awt.datatransfer.StringSelection(path),
                            null
                    );
        }
    }

    class FileIconRenderer extends DefaultTableCellRenderer{

        FileSystemView view = FileSystemView.getFileSystemView();

        public Component getTableCellRendererComponent(
                JTable table,
                Object value,
                boolean selected,
                boolean focus,
                int row,
                int column){

            JLabel label = (JLabel) super.getTableCellRendererComponent(
                    table,value,selected,focus,row,column
            );

            int modelRow = table.convertRowIndexToModel(row);

            String path = model.getValueAt(modelRow,3).toString();

            File file = new File(path);

            label.setIcon(view.getSystemIcon(file));

            return label;
        }
    }
}