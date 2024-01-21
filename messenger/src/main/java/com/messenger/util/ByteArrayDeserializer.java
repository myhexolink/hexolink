//package com.messenger.util;
//
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
//
//import java.io.IOException;
//import java.util.Base64;
//
//public class ByteArrayDeserializer extends StdDeserializer<byte[]> {
//
//    private static final long serialVersionUID = 1514703510863497028L;
//
//    public ByteArrayDeserializer() {
//        super(byte[].class);
//    }
//
//    @Override
//    public byte[] deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
//        JsonNode node = p.getCodec().readTree(p);
//        String base64 = node.asText();
//        return Base64.getDecoder().decode(base64);
//    }
//}
