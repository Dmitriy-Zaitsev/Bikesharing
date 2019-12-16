package by.epam.bikesharing.logic;

import java.io.File;
import java.io.FileInputStream;

public class ImageLoader {

//    public void insertImage(String imagePath)
//    {
//        String query;
//        try
//        {
//            File file = new File(imagePath);
//            FileInputStream stream = new FileInputStream(file);
//            long length = file.length();
//
//            query = ("insert into TableImage VALUES(?, ?, ?)");
//            pstmt = conn.prepareStatement(query);
//            pstmt.setString(1,file.getName());
//            pstmt.setInt(2, len);
//            pstmt.setBinaryStream(3, stream, length);
//            pstmt.executeUpdate();
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }

//    public byte[] getImageData()
//    {
//        byte[] fileBytes;
//        String query;
//        try
//        {
//            query = "select data from tableimage";
//            Statement state = conn.createStatement();
//            ResultSet rs = state.executeQuery(query);
//            if (rs.next())
//            {
//                fileBytes = rs.getBytes(1);
//                return rs.getBytes(1);
//            }
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }
}