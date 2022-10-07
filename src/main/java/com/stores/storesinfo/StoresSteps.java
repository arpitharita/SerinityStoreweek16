package com.stores.storesinfo;

import com.stores.constants.EndPoints;
import com.stores.model.StoresPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class StoresSteps {

    @Step("Creating stores with name :{0},type :{1},address :{2},address2 :{3},city :{4},state :{5},zip :{6},lat :{7},lng :{8}")
    public ValidatableResponse createStores(String name, String type,String address,String address2,String city,String state,String zip,float lat,float lag)
    {
        StoresPojo storesPojo =new StoresPojo();
        storesPojo.setName(name);
        storesPojo.setType(type);
        storesPojo.setAddress(address);
        storesPojo.setAddress2(address2);
        storesPojo.setCity(city);
        storesPojo.setState(state);
        storesPojo.setZip(zip);
        storesPojo.setLat(lat);
        storesPojo.setLng(lag);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(storesPojo)
                .when()
                .post()
                .then().log().all().statusCode(201);

    }
    @Step("getting stores info by name:{0}")
    public HashMap<String,Object> getStoresInfoByName(String name)
    {
        String s1 = "data.findAll{it.name='";
        String s2 = "'}.get(0)";

        return SerenityRest.given().log().all()
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .path(s1+name+s2);

    }
    @Step("update stores info with storeId :{0},name :{1},type :{2},address :{3},address2 :{4},city :{5},state :{6},zip :{7},lat :{8},lng :{9}")
    public ValidatableResponse updateStores(int storeId,String name, String type,String address,String address2,String city,String state,String zip,float lat,float lag)
    {
        StoresPojo storesPojo =new StoresPojo();
        storesPojo.setName(name);
        storesPojo.setType(type);
        storesPojo.setAddress(address);
        storesPojo.setAddress2(address2);
        storesPojo.setCity(city);
        storesPojo.setState(state);
        storesPojo.setZip(zip);
        storesPojo.setLat(lat);
        storesPojo.setLng(lag);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("storeId",storeId)
                .body(storesPojo)
                .when()
                .put(EndPoints.UPDATE_STORES_BY_ID)
                .then();

    }
    @Step("deleting stores information with storeId :{0}")
    public ValidatableResponse deleteStoresInfoById(int storeId)
    {
        return SerenityRest.given()
                .pathParam("storeId",storeId)
                .when()
                .delete(EndPoints.DELETE_STORES_BY_ID)
                .then();

    }
    @Step("getting stores info by storesid:{0}")
    public ValidatableResponse getStoresByStoresId(int storeId)
    {
        return SerenityRest.given().log().all()
                .pathParam("storeId",storeId)
                .when()
                .get(EndPoints.GET_SINGLE_STORES_BY_ID)
                .then();

    }

}
