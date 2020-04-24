package testcases;

import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.http.client.methods.CloseableHttpResponse;
import utility.TestUtils;
import utility.constants;
import java.io.IOException;
import java.util.LinkedList;

import static utility.TestUtils.*;

public class Repair_Product_Type_GetResponse_API_Test extends TestBase {
    private CloseableHttpResponse HttpResponse=null;
    private LinkedList<String> AllURLs;
    private int url_List_size;

    @BeforeMethod
    public void SetupURL() {
        AllURLs = Add_all_URLs_in_List();
        url_List_size = AllURLs.size();
    }

    @Test(priority = 1)
    public void GetStatusResponseOfAllAPIs() throws IOException {
        for (int i = 0; i < url_List_size; i++) {
            int statusCode = TestUtils.Get_StatusCode_from_JSON_response(HttpResponse, AllURLs.get(i));
            Assert.assertEquals(statusCode, constants.RESPONSE_STATUS_CODE_200, TestUtils.Get_Code_Error_Message(constants.RESPONSE_STATUS_CODE_200 + " " + " and API Failed is--" + AllURLs.get(i)));
        }
    }


    @Test(priority = 2, groups = {"RepairType", "sanity"}, dependsOnMethods = "GetStatusResponseOfAllAPIs")
    public void ValidateCountOfRepairType() throws IOException {
        String count = TestUtils.getValueByJPath(Get_JSONString_Response(HttpResponse, Get_Single_URL("Repair_Type_URL")), constants.Repair_ProductCountJpath);
        Assert.assertEquals(count, constants.Repair_Type_Count);
    }

    @Test(priority = 3, groups = {"ProductType", "sanity"}, dependsOnMethods = "GetStatusResponseOfAllAPIs")
    public void ValidateCountOfProductType() throws IOException {
        String count = TestUtils.getValueByJPath(Get_JSONString_Response(HttpResponse, Get_Single_URL("Product_Type_URL")), constants.Repair_ProductCountJpath);
        Assert.assertEquals(count, constants.Product_Type_Count); }
}
