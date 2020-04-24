package utility;

import base.TestBase;
import client.RestClient;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.IOException;
import java.util.*;

public class TestUtils extends TestBase {

    public static String Get_Code_Error_Message(String code) {
        return "Status code is not " + code;
    }

    public static String getValueByJPath(JSONObject responsejson, String jpath) {
        Object obj = responsejson;
        for (String s : jpath.split("/"))
            if (!s.isEmpty())
                if (!(s.contains("[") || s.contains("]")))
                    obj = ((JSONObject) obj).get(s);
                else if (s.contains("[") || s.contains("]"))
                    obj = ((JSONArray) ((JSONObject) obj).get(s.split("\\[")[0])).get(Integer.parseInt(s.split("\\[")[1].replace("]", "")));
        return obj.toString();
    }

    public static int Get_StatusCode_from_JSON_response(CloseableHttpResponse closeableHttpResponse, String URL) throws IOException {
        closeableHttpResponse = RestClient.get(URL);
        return closeableHttpResponse.getStatusLine().getStatusCode();
    }

    public static JSONObject Get_JSONString_Response(CloseableHttpResponse closeableHttpResponse, String URL) throws IOException {
        closeableHttpResponse = RestClient.get(URL);
        String responseString = null;
        try {
            responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject responseJson = new JSONObject(responseString);
        return responseJson;
    }

    public static ArrayList<String> Get_Add_JSONResponse_InArray(JSONObject responseObject, String results){
        JSONArray resultsArray = responseObject.getJSONArray(results);
        String resultString;
        ArrayList<String> data = new ArrayList<String>();
        for (int i=0; i<resultsArray.length(); i++) {
            resultString = resultsArray.getString(i);
            data.add(resultString);
        }
        return data;
    }


    public static void Get_Header_Data_From_JSONResponse(CloseableHttpResponse closeableHttpResponse, String URL, HashMap<String, String> headers) throws IOException {
        Get_JSONString_Response(closeableHttpResponse, URL);
        closeableHttpResponse = RestClient.get(URL, headers);
        Header[] headersArray = closeableHttpResponse.getAllHeaders();
        HashMap<String, String> allHeaders = new HashMap<String, String>();
        for (Header header : headersArray) {
            allHeaders.put(header.getName(), header.getValue());
        }
        System.out.println(allHeaders);
    }

    public static Set<Object> Get_All_URLs_Keys() {
        return prop.keySet();
    }

    public static String getPropertyValue(String key) {
        return prop.getProperty(key);
    }

    public static LinkedList<String> Add_all_URLs_in_List() {
        Set<Object> keys = Get_All_URLs_Keys();
        LinkedList<String> url_list = new LinkedList<String>();
        for (Object k : keys) {
            String key = (String) k;
           url_list.add(staging_host + getPropertyValue(key));
        }
        return url_list; }

        public static String Get_Single_URL(String KeyURL){
            return staging_host+prop.getProperty(KeyURL); }

}

