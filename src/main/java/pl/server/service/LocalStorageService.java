package pl.server.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class LocalStorageService {

    @Value("${localStoragePath}")
    private String localStoragePath;

    public String uploadFile(MultipartFile multipartFile,String z) {
        String fileURL = "";
        try {
            File file = convertMultipartFileToFile(multipartFile);
            String fileName = multipartFile.getOriginalFilename();
            fileURL = localStoragePath + "/" + z;
            uploadFileToLocalStorage(fileName, multipartFile,z);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileURL;
    }

    private File convertMultipartFileToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(file.getBytes());
        fos.close();
        return convertedFile;
    }

    private void uploadFileToLocalStorage(String fileName, MultipartFile file, String z) throws IOException {
        File localStorageFile = new File(localStoragePath + "/" +z+".jpg");
        System.out.println("zebi "+ z+".jpg");
        System.out.println("filename "+ fileName);
        FileOutputStream fos = new FileOutputStream(localStorageFile);
        fos.write(file.getBytes());
        fos.close();
    }

    public byte[] downloadFileFromLocalStorage(String fileName) throws IOException {
        File localStorageFile = new File(localStoragePath + "/" + fileName);
        FileInputStream fis = new FileInputStream(localStorageFile);
        byte[] fileBytes = new byte[(int) localStorageFile.length()];
        fis.read(fileBytes);
        fis.close();
        return fileBytes;
    }

    public String deleteFileFromLocalStorage(String fileName) {
        File localStorageFile = new File(localStoragePath + "/" + fileName);
        if (localStorageFile.delete()) {
            return "Deletion Successful";
        } else {
            return "Deletion Failed";
        }
    }
}
