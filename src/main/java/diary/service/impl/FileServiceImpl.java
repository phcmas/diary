package diary.service.impl;

import diary.dao.algorithm.AlgorithmDao;
import diary.dao.algorithm.FileInfoDao;
import diary.dto.algorithm.FileInfo;
import diary.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileInfoDao fileInfoDao;

    @Autowired
    AlgorithmDao algorithmDao;

    @Override
    public FileInfo get(int id) {
        return fileInfoDao.get(id);
    }

    @Override
    public FileInfo getByAlgorithmId(int algorithmId) {
        return fileInfoDao.getByAlgorithmId(algorithmId);
    }

    @Override
    @Transactional
    public int add(int algorithmId, MultipartFile file) {
        storeFile(file);
        return addFileInfo(algorithmId, file);
    }

    @Override
    @Transactional
    public int delete(int id) {
        FileInfo fileInfo = fileInfoDao.get(id);
        if (fileInfo != null) {
            deleteFile(fileInfo.getSaveFileName());
            return deleteFileInfo(id);
        }

        return -1;
    }

    @Override
    @Transactional
    public int update(int id, int algorithmId, MultipartFile file) {
        FileInfo fileInfo = fileInfoDao.get(id);
        int ret = -1;

        if (fileInfo != null) {
            deleteFile(fileInfo.getSaveFileName());
            ret = updateFileInfo(id, algorithmId, file);
        } else {
            ret = addFileInfo(algorithmId, file);
        }

        storeFile(file);
        return ret;
    }

    @Override
    public int addFileInfo(int algorithmId, MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String saveFileName = fileRoot + fileName;
        String contentType = file.getContentType();

        FileInfo fileInfo = FileInfo.builder().algorithmId(algorithmId)
                .fileName(fileName).saveFileName(saveFileName)
                .contentType(contentType).build();

        return fileInfoDao.add(fileInfo);
    }

    @Override
    public int deleteFileInfo(int id) {
        return fileInfoDao.delete(id);
    }

    @Override
    public int updateFileInfo(int id, int algorithmId, MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String saveFileName = fileRoot + fileName;
        String contentType = file.getContentType();

        FileInfo fileInfo = FileInfo.builder().id(id)
                .algorithmId(algorithmId).fileName(fileName)
                .saveFileName(saveFileName).contentType(contentType).build();

        return fileInfoDao.update(fileInfo);
    }

    @Override
    public void storeFile(MultipartFile file) {
        int readCount = 0;
        String saveFileName = fileRoot + file.getOriginalFilename();
        byte[] buffer = new byte[1024];

        try (FileOutputStream fos = new FileOutputStream(saveFileName);
             InputStream is = file.getInputStream();) {
            while ((readCount = is.read(buffer)) != -1) {
                fos.write(buffer, 0, readCount);
            }
        } catch (Exception e) {
            throw new RuntimeException("File Save Error");
        }
    }

    @Override
    public void deleteFile(String saveFileName) {
        File file = new File(saveFileName);
        file.delete();
    }

    @Override
    public boolean findFile(String fileName) {
        File file = new File(fileRoot + fileName);
        return file.exists();
    }

}
