import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LinkPuller implements ActionListener {

    public JFrame frame;
    public JPanel panel;
    public JPanel panel2;
    public JPanel panel3;
    public JButton goButton;
    public JTextField urlInput;
    public JTextField search;
    public JTextArea results;

    String URL = new String();
    String searchTerm = new String();

    public static void main(String[] args) {
        LinkPuller html = new LinkPuller();

    }

    public LinkPuller() {
        frame = new JFrame("Katelyn's Link Puller");
        panel = new JPanel(new BorderLayout());
        panel2 = new JPanel(new BorderLayout());
        panel3 = new JPanel(new GridLayout(2, 1));
        goButton = new JButton("GO");
        goButton.addActionListener(this);

        urlInput = new JTextField("type url here");
        search = new JTextField("search here");
        results = new JTextArea("your response will appear here");
        panel.add(results, BorderLayout.CENTER);
        panel.add(panel2, BorderLayout.NORTH);
        panel2.add(panel3, BorderLayout.CENTER);
        panel3.add(urlInput);
        panel3.add(search);
        panel2.add(goButton, BorderLayout.EAST);

        frame.add(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Button clicked");
        String t = urlInput.getText();
//        urlInput.setText(t);
        search();
    }

    public void search() {
        try {
            System.out.println();
            System.out.print("hello \n");
            searchTerm = search.getText();
            URL = urlInput.getText();
            URL url = new URL(URL);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream())
            );
            String line;
            int c = 1;
            while ((line = reader.readLine()) != null) {
                if (line.contains(searchTerm) && line.contains("href")) {
//                    System.out.println(line);
                    int start = line.indexOf("href") + 6;
                    while (start != -1) {
                        String miniline = line.substring(start);
                        int end = miniline.indexOf("\"") -1;
                        String URL = miniline.substring(0, end);
                        System.out.println(c + ". " + URL);
                        c++;
                        start = miniline.indexOf("href",end);
                        System.out.println("start: " + start);
//                        break;
                    }
                }
            }
            reader.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}

