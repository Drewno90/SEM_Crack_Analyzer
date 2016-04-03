import java.io.File;

import javax.swing.filechooser.FileFilter;

public class BMPImageFileFilter extends FileFilter implements java.io.FileFilter
 {
 public boolean accept(File f)
   {
   if (f.getName().toLowerCase().endsWith(".bmp")) return true;

   if(f.isDirectory())return true;
   return false;
  }
 public String getDescription()
   {
   return "BMP files";
   }

} 