package com.kreitek.files;

import com.kreitek.files.error.InvalidFileFormatException;

import java.util.List;

public  class File extends FileSystemItemBase implements FileExtension, FileOperations,FilePosition {
    private boolean isOpen = false;
    private int position = 0;
    private int size = 0;
    private List<FileSystemItemBase> files;

    public File(Directory parent, String name) {
        super(parent, name);
    }

    @Override
    public String getExtension() {
        String extension = "";
        int indexOfLastDot = getName().lastIndexOf(".");
        if (indexOfLastDot > 0) {
            extension = getName().substring(indexOfLastDot + 1);
        }
        return extension;
    }
    @Override
    public int getSize(){
        return size;
    }



    @Override
    public void setPosition(int numberOfBytesFromBeginning) {
        if (!isOpen) {
            throw new UnsupportedOperationException("Debe abrir el fichero primero");
        }
        if (numberOfBytesFromBeginning > size) {
            throw new UnsupportedOperationException("La posición no puede ser mayor que el tamaño del fichero");
        }
        this.position = numberOfBytesFromBeginning;
    }

    @Override
    public List<FileSystemItemBase> listFiles() {
        return files;
    }

    @Override
    public void open() {
        isOpen = true;
    }

    @Override
    public byte[] read(int numberOfBytesToRead) {
        if (position + numberOfBytesToRead > size) {
            numberOfBytesToRead = size - position;
        }
        byte[] buffer = new byte[numberOfBytesToRead];
        position += numberOfBytesToRead;
        return buffer;
    }

    @Override
    public void write(byte[] buffer) {
        size += buffer.length;
        position += buffer.length;
    }

    @Override
    public void close() {
        isOpen = false;
    }


}
