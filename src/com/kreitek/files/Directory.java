package com.kreitek.files;

import com.kreitek.service.FileManager;
import java.util.ArrayList;
import java.util.List;

public class Directory extends FileSystemItemBase implements FileChanges, FileList {

    private final List<FileSystemItemBase> files;

    public Directory(Directory parent, String name) {
        super(parent, name);
        files = new ArrayList<>();
    }

    @Override
    public int getSize() {

        return FileManager.calculateSize(files);
    }

    @Override
    public void addFile(FileSystemItemBase file) {
        if (!files.contains(file)) {
            files.add(file);
            file.setParent(this);
        }
    }

    @Override
    public void removeFile(FileSystemItemBase file) {
        files.remove(file);
    }

    @Override
    public List<FileSystemItemBase> listFiles() {
        return files;
    }

}
