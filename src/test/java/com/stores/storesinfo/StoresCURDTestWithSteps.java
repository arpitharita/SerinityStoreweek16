package com.stores.storesinfo;

import com.stores.testbase.TestBase;
import com.stores.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class StoresCURDTestWithSteps extends TestBase {

    static String name = "Maple" + TestUtils.getRandomValue();
    static String type = "BigBox" + TestUtils.getRandomValue();
    static String address = "1795 County Rd D E" + TestUtils.getRandomValue();

    static String address2 = "";
    static String city = "Maplewood" + TestUtils.getRandomValue();
    static String state = "MN";
    static String zip = "55109";
    static float lat = 45.036556f;
    static float lag = -93.025986f;

    static int storeId;

    @Steps
    StoresSteps storesSteps;
    @Title("This will create a new stores")
    @Test
    public void test001()
    {
        ValidatableResponse response = storesSteps.createStores(name,type,address,address2,city,state,zip,lat,lag);
        response.log().all().statusCode(201);

    }

    @Title("Verify if stores is created")
    @Test
    public void test002()
    {
        HashMap<String,Object> storesMap = storesSteps.getStoresInfoByName(name);
        Assert.assertThat(storesMap, hasValue(name));
        storeId = (int) storesMap.get("id");
        System.out.println(storeId);

    }
    @Title("update the stores information")
    @Test
    public void test003()
    {
        name = name+"update";
        storesSteps.updateStores(storeId,name,type,address,address2,city,state,zip,lat,lag);
        HashMap<String,Object> storesMap = storesSteps.getStoresInfoByName(name);
        Assert.assertThat(storesMap, hasValue(name));

    }
    @Title("Delete stores info by storesid and verify its deleted")
    @Test
    public void test004()
    {
        storesSteps.deleteStoresInfoById(storeId).log().all().statusCode(200);
        storesSteps.getStoresByStoresId(storeId).log().all().statusCode(404);

    }
}
