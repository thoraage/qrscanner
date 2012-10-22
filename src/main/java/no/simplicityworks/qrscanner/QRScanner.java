package no.simplicityworks.qrscanner;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.*;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.GenericMultipleBarcodeReader;

public class QRScanner {

    public QRScanner() {
        JFrame frame = new JFrame("QR Scanner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = frame.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        JButton scanButton = new JButton("Scan");
        scanButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        pane.add(scanButton);
        final JLabel statusLabel = new JLabel("Status");
        final JTextArea resultTestArea = new JTextArea(10, 80);
        JScrollPane scrollPane = new JScrollPane(resultTestArea);
        scanButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Image image = Clipboard.getImageFromClipboard();
                    if (image == null) {
                        statusLabel.setText("No image on clipboard");
                        return;
                    }
                    BufferedImageLuminanceSource source = new BufferedImageLuminanceSource((BufferedImage) image);
                    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                    MultiFormatReader multiFormatReader = new MultiFormatReader();
                    GenericMultipleBarcodeReader reader = new GenericMultipleBarcodeReader(multiFormatReader);
                    Result[] results = reader.decodeMultiple(bitmap);
                    String s = "";
                    for (Result result : results) {
                        s += result.toString() + "\n";
                    }
                    resultTestArea.setText(s);
                } catch (NotFoundException e1) {
                    statusLabel.setText("Nothing found in image");
                }
            }
        });
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        pane.add(scrollPane);
        statusLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        pane.add(statusLabel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new QRScanner();
    }

}
