package com.solt_inc.model.module;

import org.apache.wicket.markup.html.WebPage;

public class PictureUpload extends WebPage {

    

        public PictureUpload() {
        }
//
//        public String uploadPicture( )  {
//
//
//        String fileName = null;
//        ServletContext context = this.getServletContext();
//        String path = context.getRealPath(getString(""));
//        System.out.println(path);
//
//        DiskFileItemFactory factory = new DiskFileItemFactory();
//        ServletFileUpload upload = new ServletFileUpload(factory);
//
//        factory.setSizeThreshold(1024);
//        upload.setSizeMax(-1);
//        upload.setHeaderEncoding("Windows-31J");
//
//        try {
//            List<FileItem> list = upload.parseRequest(request);
//            Iterator<FileItem> iterator = list.iterator();
//            while(iterator.hasNext()) {
//                FileItem fItem = (FileItem)iterator.next();
//                if(!(fItem.isFormField())){
//                    fileName = fItem.getName();
//                     if((fileName != null) && (!fileName.equals(""))){
//                         fileName=(new File(fileName)).getName();
//                         fItem.write(new File(path + "/" + fileName));
//                         request.setAttribute("file", path + "/" + fileName);
//                     }
//                }
//
//            }
//
//        } catch(FileUploadException e) {
//            e.printStackTrace();
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//        return fileName;
//    }
}
