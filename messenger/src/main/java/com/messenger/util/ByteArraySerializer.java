//package com.messenger.util;
//
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.databind.SerializerProvider;
//import com.fasterxml.jackson.databind.ser.std.StdSerializer;
//
//import java.io.IOException;
//import java.util.Base64;
//
//public class ByteArraySerializer extends StdSerializer<byte[]> {
//
//    public ByteArraySerializer() {
//        super(byte[].class);
//    }
//
//    @Override
//    public void serialize(byte[] value, JsonGenerator gen, SerializerProvider provider) throws IOException {
//        gen.writeString(Base64.getEncoder().encodeToString(value));
//    }
//
//}
