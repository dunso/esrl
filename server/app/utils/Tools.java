package utils;

import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Tools {

    public static ObjectNode buildJsonResponse(int code, Object data) {
        ObjectNode resp = Json.newObject();
        resp.put("code", code);
        resp.putPOJO("data", data);
        return resp;
    }

    public static ObjectNode buildJsonResponse(int code, String msg) {
        ObjectNode resp = Json.newObject();
        resp.put("code", code);
        resp.put("msg", msg);
        return resp;
    }

    public static byte[] getSha512(String value) {
        try {
            return MessageDigest.getInstance("SHA-512").digest(value.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
