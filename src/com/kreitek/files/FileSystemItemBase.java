package com.kreitek.files;

import java.util.ArrayList;
import java.util.List;

public abstract class FileSystemItemBase{
    protected static final String PATH_SEPARATOR = "/";
    private String name;
    private Directory parent;
    private String fullPath = PATH_SEPARATOR;
    private int size;
    private final List<FileSystemItemBase> files;

    protected FileSystemItemBase(Directory parent, String name) {
        this.parent = parent;
        this.name = name;
        files = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FileSystemItemBase getParent(){
        return parent;
    }

    public void setParent(Directory parent) {
        this.parent = parent;
    }

    //TODO corregir este método
    public String getFullPath() {
        if (parent != null) {
            String parentFullPath = parent.getFullPath();
            fullPath = parent.getFullPath() + (parentFullPath.length() > 1 ? PATH_SEPARATOR : "");
        }
        fullPath = fullPath + getName();
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public abstract int getSize();

    public void setSize(int size) {
        this.size = size;
    }

    /*public List<FileSystemItemBase> listFiles() {
        return files;
    }*/

    public abstract List<FileSystemItemBase> listFiles();


}
