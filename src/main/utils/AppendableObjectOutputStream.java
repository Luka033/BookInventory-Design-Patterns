package main.utils;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * Extension of the {@link ObjectOutputStream} to enable appending to the stream.
 * By default, the stream writes a new header each time the stream is created,
 * which causes issues during deserialization due to unexpected headers. This
 * allows appending to the same file using different instances of the object.
 */
class AppendingObjectOutputStream extends ObjectOutputStream {
    public AppendingObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    /**
     * Instead of writing a header (default behavior) reset instead.
     */
    @Override
    protected void writeStreamHeader() throws IOException {
        reset();

    }
}