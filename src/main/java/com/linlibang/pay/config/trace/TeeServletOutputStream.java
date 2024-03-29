package com.linlibang.pay.config.trace;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.WriteListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 描述:
 * 日期: 2018/7/11--18:17
 *
 * @author yanpeicai
 */
public class TeeServletOutputStream extends ServletOutputStream {

    private final ServletOutputStream underlyingStream;
    private final ByteArrayOutputStream baosCopy;

    TeeServletOutputStream(ServletResponse httpServletResponse) throws IOException {
        // System.out.println("TeeServletOutputStream.constructor() called");
        this.underlyingStream = httpServletResponse.getOutputStream();
        baosCopy = new ByteArrayOutputStream();
    }

    byte[] getOutputStreamAsByteArray() {
        return baosCopy.toByteArray();
    }

    @Override
    public void write(int val) throws IOException {
        if (underlyingStream != null) {
            underlyingStream.write(val);
            baosCopy.write(val);
        }
    }

    @Override
    public void write(byte[] byteArray) throws IOException {
        if (underlyingStream == null) {
            return;
        }
        // System.out.println("WRITE TeeServletOutputStream.write(byte[]) called");
        write(byteArray, 0, byteArray.length);
    }

    @Override
    public void write(byte[] byteArray, int offset, int length) throws IOException {
        if (underlyingStream == null) {
            return;
        }
        // System.out.println("WRITE TeeServletOutputStream.write(byte[], int, int)
        // called");
        // System.out.println(new String(byteArray, offset, length));
        underlyingStream.write(byteArray, offset, length);
        baosCopy.write(byteArray, offset, length);
    }

    @Override
    public void close() throws IOException {
        // System.out.println("CLOSE TeeServletOutputStream.close() called");

        // If the servlet accessing the stream is using a writer instead of
        // an OutputStream, it will probably call os.close() before calling
        // writer.close. Thus, the underlying output stream will be called
        // before the data sent to the writer could be flushed.
    }

    @Override
    public void flush() throws IOException {
        if (underlyingStream == null) {
            return;
        }
        // System.out.println("FLUSH TeeServletOutputStream.flush() called");
        underlyingStream.flush();
        baosCopy.flush();
    }

    @Override
    public boolean isReady() {
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public void setWriteListener(WriteListener listener) {
        throw new RuntimeException("Not yet implemented");
    }
}