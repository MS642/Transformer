package io;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface Savable {
    void save() throws FileNotFoundException, UnsupportedEncodingException;
}
