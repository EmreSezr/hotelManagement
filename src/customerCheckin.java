/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class customerCheckin extends javax.swing.JFrame {

    // Veritabanı bağlantısı ve sorgu için değişkenler
    Connection con = null;
    Statement st = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Müşteri girişi arayüzünü başlatır. Giriş tarihini bugünkü tarih olarak
     * ayarlar. Veritabanına bağlanır.
     */
    public customerCheckin() {
        initComponents();
        // Bugünkü tarihi alır ve txtcheckindate alanına yerleştirir
        SimpleDateFormat obj = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        txtcheckindate.setText(obj.format(date));
        try {
            // JDBC sürücüsünü yükler ve veritabanına bağlanır
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/otel", "root", "");
        } catch (ClassNotFoundException ex) {
            // Hata durumunda log kaydı oluşturur
            Logger.getLogger(manageRoom.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            // Hata durumunda log kaydı oluşturur
            Logger.getLogger(manageRoom.class.getName()).log(Level.SEVERE, null, ex);
        }
        // checkRoom metodu ile odaların durumları güncel olarak çağrılır.
        checkRoom();
    }

    public void checkRoom() {
        try {
            // Seçilen yatak türü, oda tipi ve rezerv durumuyla eşleşen odaları seçmek için bir sorgu hazırlanır
            pst = con.prepareStatement("select roomnumber from room where bed=? and roomtype=? and status=?");
            pst.setString(1, cmbbed.getItemAt(cmbbed.getSelectedIndex())); // Yatak türü parametresi ayarlanır
            pst.setString(2, cmbroomtype.getItemAt(cmbroomtype.getSelectedIndex())); // Oda tipi parametresi ayarlanır
            pst.setString(3, "Rezerve Yok"); // Rezerv durumu parametresi ayarlanır
            rs = pst.executeQuery(); // Sorgu çalıştırılır
            cmbroomnumber.removeAllItems(); // ComboBox temizlenir
            // Sonuçlar üzerinde döngü oluşturulur
            while (rs.next()) {
                // ComboBox'a odalar eklenir bu sayede uygun odaları görüntüleyebiliriz.
                cmbroomnumber.addItem(rs.getString("roomnumber"));
            }
            // Seçilen oda numarasına göre fiyatı almak için bir sorgu hazırlanır
            pst = con.prepareStatement("select price from room where roomnumber=?");
            // Oda numarası parametresi ayarlanır
            pst.setString(1, cmbroomnumber.getItemAt(cmbroomnumber.getSelectedIndex())); 
            rs = pst.executeQuery();
            if (rs.next()) {
                txtprice.setText(rs.getString("price")); // Fiyat alanı güncellenir
            }
        } catch (SQLException ex) {
            Logger.getLogger(customerCheckin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txttcno = new javax.swing.JTextField();
        txtname = new javax.swing.JTextField();
        cmbgender = new javax.swing.JComboBox<>();
        txtmobilephone = new javax.swing.JTextField();
        txtcheckindate = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cmbbed = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cmbroomtype = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtprice = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        txtnationality = new javax.swing.JTextField();
        cmbroomnumber = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(900, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 0, 60, 50));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Customer Registration & Check IN.png"))); // NOI18N
        jLabel1.setText("Musteri Checkin Yap");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("TC Numarasi");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 420, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Isim");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Telefon Numarasi");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Cinsiyet");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 250, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Uyruk");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, -1, -1));
        getContentPane().add(txttcno, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 450, 200, 40));
        getContentPane().add(txtname, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 200, 40));

        cmbgender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Erkek", "Kadin" }));
        cmbgender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbgenderActionPerformed(evt);
            }
        });
        getContentPane().add(cmbgender, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 280, 200, 40));

        txtmobilephone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmobilephoneActionPerformed(evt);
            }
        });
        getContentPane().add(txtmobilephone, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 200, 40));

        txtcheckindate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcheckindateActionPerformed(evt);
            }
        });
        getContentPane().add(txtcheckindate, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 100, 200, 40));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Checkin Tarihi (yyyy/aa/gg)");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Yatak Tipi");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 160, -1, -1));

        cmbbed.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tek Yatakli", "Cift Yatakli" }));
        cmbbed.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbbedİtemStateChanged(evt);
            }
        });
        cmbbed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbbedActionPerformed(evt);
            }
        });
        getContentPane().add(cmbbed, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 190, 200, 40));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Oda Tipi");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 250, -1, -1));

        cmbroomtype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "İki Kisilik Oda", "Suit Oda" }));
        cmbroomtype.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbroomtypeİtemStateChanged(evt);
            }
        });
        cmbroomtype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbroomtypeActionPerformed(evt);
            }
        });
        getContentPane().add(cmbroomtype, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 280, 200, 40));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Oda Numarasi");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 340, -1, -1));
        getContentPane().add(txtprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 450, 200, 40));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Fiyat");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 420, -1, -1));

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setText("Temizle");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 530, -1, -1));

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton3.setText("Checkin Yap");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 530, -1, -1));
        getContentPane().add(txtnationality, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 370, 200, 40));

        cmbroomnumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbroomnumberActionPerformed(evt);
            }
        });
        getContentPane().add(cmbroomnumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 370, 200, 40));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Kullanıcı anasayfasında açılan checkin panelini kapatır.
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtmobilephoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmobilephoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmobilephoneActionPerformed

    private void txtcheckindateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcheckindateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcheckindateActionPerformed

    private void cmbbedİtemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbbedİtemStateChanged
        checkRoom();
    }//GEN-LAST:event_cmbbedİtemStateChanged

    private void cmbroomtypeİtemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbroomtypeİtemStateChanged
        checkRoom();
    }//GEN-LAST:event_cmbroomtypeİtemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       // Temizle butonuna tıkladığında girilen değerleri sıfırlar.
        txtname.setText("");
        txttcno.setText("");
        txtmobilephone.setText("");
        txtnationality.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (txtname.getText().equals("")) {
            txtname.requestFocus();
            JOptionPane.showMessageDialog(this, "Alanlari Doldurmak Zorunludur.");
        } else if (txttcno.getText().equals("")) {
            txttcno.requestFocus();
            JOptionPane.showMessageDialog(this, "Alanlari Doldurmak Zorunludur.");
        } else if (txtmobilephone.getText().equals("")) {
            txtmobilephone.requestFocus();
            JOptionPane.showMessageDialog(this, "Alanlari Doldurmak Zorunludur.");
        } else if (txtnationality.getText().equals("")) {
            txtnationality.requestFocus();
            JOptionPane.showMessageDialog(this, "Alanlari Doldurmak Zorunludur.");
        } else {
            try {
                // Kullanıcıdan aldığımız değerleri sql sorgusu ile veritabanına gönderiyoruz.
                pst = con.prepareStatement("insert into customer(name,mobile,gender,id,nationality,joindate,roomnumber,bed,roomtype,price,status)values(?,?,?,?,?,?,?,?,?,?,?)");
                pst.setString(1, txtname.getText());
                pst.setString(2, txtmobilephone.getText());
                pst.setString(3, cmbgender.getItemAt(cmbgender.getSelectedIndex()));
                pst.setString(4, txttcno.getText());
                pst.setString(5, txtnationality.getText());
                pst.setString(6, txtcheckindate.getText());
                pst.setString(7, cmbroomnumber.getItemAt(cmbroomnumber.getSelectedIndex()));
                pst.setString(8, cmbbed.getItemAt(cmbbed.getSelectedIndex()));
                pst.setString(9, cmbroomtype.getItemAt(cmbroomtype.getSelectedIndex()));
                pst.setString(10, txtprice.getText());
                pst.setString(11, "NULL");
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Checkin Basarili.");
                //Değerler databaseye gönderildikten sonra
                //kullanış kolaylığı açısından girilenleri temizliyoruz.
                txtname.setText("");
                txttcno.setText("");
                txtmobilephone.setText("");
                txtnationality.setText("");
                // Oda durumu güncellenir (Rezerve Edilmiş olarak)
                pst = con.prepareStatement("update room set status=? where roomnumber=?");
                pst.setString(1, "Rezerve Edilmis");
                // Oda numarası parametresi ayarlanır
                pst.setString(2, cmbroomnumber.getItemAt(cmbroomnumber.getSelectedIndex()));
                pst.executeUpdate();
                // Güncellenen değerleri tekrardan methoda gönderiyoruz.
                // Bu sayede Checkin yaptıktan sonra o odayı tekrar seçemiyoruz.
                checkRoom();

            } catch (SQLException ex) {
                Logger.getLogger(customerCheckin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cmbroomtypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbroomtypeActionPerformed
        checkRoom();
    }//GEN-LAST:event_cmbroomtypeActionPerformed

    private void cmbbedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbbedActionPerformed
        checkRoom();
    }//GEN-LAST:event_cmbbedActionPerformed

    private void cmbgenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbgenderActionPerformed
        checkRoom();
    }//GEN-LAST:event_cmbgenderActionPerformed

    private void cmbroomnumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbroomnumberActionPerformed
         checkRoom();
    }//GEN-LAST:event_cmbroomnumberActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(customerCheckin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(customerCheckin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(customerCheckin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(customerCheckin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new customerCheckin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbbed;
    private javax.swing.JComboBox<String> cmbgender;
    private javax.swing.JComboBox<String> cmbroomnumber;
    private javax.swing.JComboBox<String> cmbroomtype;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtcheckindate;
    private javax.swing.JTextField txtmobilephone;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtnationality;
    private javax.swing.JTextField txtprice;
    private javax.swing.JTextField txttcno;
    // End of variables declaration//GEN-END:variables
}
