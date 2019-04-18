package org.gitlab4j.api;

import java.io.IOException;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;

public class MockServletInputStream extends ServletInputStream {

    private byte[] inputBytes;
    private int lastIndexRetrieved = -1;
    private ReadListener readListener = null;

    public MockServletInputStream(String data) {
        inputBytes = data.getBytes();
    }

    @Override
    public boolean isFinished() {
        return (lastIndexRetrieved == inputBytes.length-1);
    }

    @Override
    public boolean isReady() {
        // This implementation will never block
        // We also never need to call the readListener from this method, as this method will never return false
        return isFinished();
    }

    @Override
    public void setReadListener(ReadListener readListener) {
        this.readListener = readListener;
        if (!isFinished()) {
            try {
                readListener.onDataAvailable();
            } catch (IOException e) {
                readListener.onError(e);
            }
        } else {
            try {
                readListener.onAllDataRead();
            } catch (IOException e) {
                readListener.onError(e);
            }
        }
    }

    @Override
    public int read() throws IOException {
        int i;
        if (!isFinished()) {
            i = inputBytes[lastIndexRetrieved+1];
            lastIndexRetrieved++;
            if (isFinished() && (readListener != null)) {
                try {
                    readListener.onAllDataRead();
                } catch (IOException ex) {
                    readListener.onError(ex);
                    throw ex;
                }
            }
            return i;
        } else {
            return -1;
        }
    }
}