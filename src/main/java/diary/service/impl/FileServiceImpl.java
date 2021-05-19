package diary.service.impl;

import diary.dao.algorithm.AlgorithmDao;
import diary.dao.algorithm.FileInfoDao;
import diary.dto.algorithm.FileInfo;
import diary.service.AlgorithmService;
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

    private final String fileRoot = "/home/seungkyun/Web/diary/files/";

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
        deleteFile(fileInfo.getSaveFileName());
        return deleteFileInfo(id);
    }

    @Override
    @Transactional
    public int update(int id, MultipartFile file) {
        FileInfo fileInfo = fileInfoDao.get(id);
        deleteFile(fileInfo.getSaveFileName());
        storeFile(file);
        return updateFileInfo(id, fileInfo.getAlgorithmId(), file);
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
}
